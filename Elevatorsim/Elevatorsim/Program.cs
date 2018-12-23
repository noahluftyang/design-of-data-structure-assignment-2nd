using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Elevatorsim
{
    static class Debug
    {
        static StringBuilder dbgstr = new StringBuilder();
        static public string PeekStr()
        {
            return dbgstr.ToString();
        }
        static public void Log(string str)
        {
            dbgstr.Append(str);
        }
        static public string PopStr()
        {
            string str = dbgstr.ToString();
            dbgstr = new StringBuilder();
            return str;
        }
    }

    class Program
    {
        const int maxCycle = 36000;
        const string filedir = "./elevatorSim.csv";
        const string readfiledir = "./evinfo.csv";

        static Floor GetFloor(int i)
        {
            return floors[i+5];
        }
        static Floor[] floors = new Floor[] {
            new Floor(){ name = "B6 " },
            new Floor(){ name = "B5 " },
            new Floor(){ name = "B4 " },
            new Floor(){ name = "B3 " },
            new Floor(){ name = "B2 " },
            new Floor(){ name = "B1 " },
            new Floor(){ name = "1st" },
            new Floor(){ name = "2nd" },
            new Floor(){ name = "3rd" },
            new Floor(){ name = "4th" },
            new Floor(){ name = "5th" },
            new Floor(){ name = "6th" },
            new Floor(){ name = "7th" },
            new Floor(){ name = "8th" },
            new Floor(){ name = "9th" },
        };
        static bool print;
        static EvWeeklyData evWeeklyData;
        static void Main(string[] args)
        {
            evWeeklyData = new EvWeeklyData(readfiledir);
            Console.WriteLine("print realtime ev info?(demo purpose)");
            Console.Write("y to yes, everything else to no :");
            string readLine = Console.ReadLine();
            if (readLine.Equals("y"))
                print = true;
            else
                print = false;

            foreach (DayOfWeek w in (DayOfWeek[])Enum.GetValues(typeof(DayOfWeek)))
            {
                Simulation(w.ToString() + "_a", w, 'a');
                Simulation(w.ToString() + "_b", w, 'b');
                Simulation(w.ToString() + "_c", w, 'c');
            }

        }
        static void Simulation(string writeFileName, DayOfWeek week, char evcode)
        {
            writeFileName += ".csv";
            /*
            List<int> personcount = new List<int>();
            List<int> personcountdelta = new List<int>();*/
            int cycle = 0;
            Random rng = new Random();
            Elevator[] elevators = new Elevator[] {
                new Elevator() { currnetfloor = 0, maxPassengers = 20, minFloor = 0, passengerCount = 0 },
                new Elevator() { currnetfloor = 2, maxPassengers = 20, minFloor = 1, passengerCount = 0 },
                new Elevator() { currnetfloor = 4, maxPassengers = 20, minFloor = 1, passengerCount = 0 },
                new Elevator() { currnetfloor = 5, maxPassengers = 20, minFloor = 1, passengerCount = 0 }
            };
            if(evcode == 'c')
                elevators = new Elevator[] {
                new Elevator() { currnetfloor = 0, maxPassengers = 20, minFloor = 0, passengerCount = 0 },
                new Elevator() { currnetfloor = 2, maxPassengers = 20, minFloor = 1, passengerCount = 0 }
            };

            using (FileStream fs = File.Create(writeFileName))
            {
                for (; cycle < maxCycle; cycle++) // 사이클 돌림
                {
                    int h = cycle / 3600 + 8;
                    int m = (cycle / 60) % 60;
                    if (print)
                    {
                        Console.WriteLine("cycle: " + cycle);
                        //print stat
                        Console.WriteLine("+--+--+--+--+-----------------+");
                        for (int floor = floors.Length - 1; floor >= 0; floor--)
                        {
                            Console.Write("|" + elevators[0].PrintFloorInfo(floor));
                            Console.Write("|" + elevators[1].PrintFloorInfo(floor));
                            Console.Write("|" + elevators[2].PrintFloorInfo(floor));
                            Console.Write("|" + elevators[3].PrintFloorInfo(floor));
                            Console.WriteLine("|" + floors[floor].AllUser);


                            Console.WriteLine("+" + elevators[0].goingData(floor) +
                                "+" + elevators[1].goingData(floor) +
                                "+" + elevators[2].goingData(floor) +
                                "+" + elevators[3].goingData(floor)
                                + "+-----------------+");
                        }

                        Console.WriteLine("press enter to continue");
                        Console.ReadLine();
                    }
                    foreach (var floor in floors)//매 층마다
                    {
                        floor.DoTick();
                        if (rng.Next() % 600 == 0)//10분에 1명꼴로 랜덤탑승
                            floor.userWaiting += 1;
                        if (m < 50 && m > 40 && rng.Next() % 10 == 0)//수업끝
                        {
                            if (Array.IndexOf(floors, floor) >= 7) // 2층이상
                            {
                                float wd = evWeeklyData[Array.IndexOf(floors, floor), h, week, evcode];
                                int ff = wd == 0 ? 0 : (int)(rng.Next() % wd);
                                floor.userWaiting += ff;
                            }

                        }
                        else if (m > 50 && rng.Next() % 10 == 0)//수업시작시점
                        {
                            if (7 > Array.IndexOf(floors, floor))
                            {
                                float wd = evWeeklyData[Array.IndexOf(floors, floor), h, week, evcode];
                                int ff = wd == 0 ? 0 : (int)(rng.Next() % wd);
                                floor.userWaiting += ff;
                            }
                        }
                    }
                    foreach (var ev in elevators)
                    {
                        ev.waiting -= 1;
                        Elevator.Action prevaction = ev.action;
                        if (ev.waiting <= 0)
                        {
                            if (ev.action == Elevator.Action.going)
                            {
                                int passengers = 0;
                                if (ev.currnetfloor == 0)
                                {
                                    int unloaded = ev.UnloadUsers(1);//100% 내림
                                    floors[ev.currnetfloor].userGoing += unloaded;
                                    passengers += unloaded;
                                    passengers += floors[ev.currnetfloor].LoadUserTo(ev);
                                }
                                else if (ev.currnetfloor == floors.Length - 1)
                                {
                                    passengers += ev.UnloadUsers(1);//100% 내림
                                    passengers += floors[ev.currnetfloor].LoadUserTo(ev);
                                }
                                else if (ev.going == Elevator.Direction.up)
                                {
                                    int unloaded = 0;
                                    if (ev.currnetfloor < 10)
                                        unloaded = ev.UnloadUsers(0.05f);
                                    else
                                        unloaded = ev.UnloadUsers(0.7f);

                                    floors[ev.currnetfloor].userGoing += unloaded;
                                    passengers += unloaded;
                                }
                                else if (ev.going == Elevator.Direction.down)
                                {
                                    int unloaded = 0;
                                    if (ev.currnetfloor == 6)//1층
                                        unloaded = ev.UnloadUsers(0.7f);
                                    else if (ev.currnetfloor < 2)//B4층 미만
                                        unloaded = ev.UnloadUsers(0.9f);
                                    else if (ev.currnetfloor < 6)//1층 미만
                                        unloaded = ev.UnloadUsers(0.2f);
                                    if (ev.passengerCount == ev.maxPassengers)
                                        passengers -= 9;
                                    else
                                    {
                                        passengers += floors[ev.currnetfloor].userWaiting;
                                        floors[ev.currnetfloor].LoadUserTo(ev);
                                    }
                                    passengers += unloaded;
                                }

                                ev.currnetfloor += ev.going == Elevator.Direction.up ? 1 : -1;
                                ev.action = Elevator.Action.loading;


                                ev.waiting = passengers < 0 ? 0 : (passengers > ev.maxPassengers ? ev.maxPassengers : passengers) / 3 + 7;
                            }

                            else if (ev.action == Elevator.Action.loading)
                            {
                                if (ev.currnetfloor == floors.Length - 1)//7층
                                    ev.going = Elevator.Direction.down;
                                else if (ev.currnetfloor == 0)//1층
                                    ev.going = Elevator.Direction.up;

                                ev.action = Elevator.Action.going;
                                ev.waiting = 4;
                            }

                        }

                    }

                    StringBuilder sb = new StringBuilder();

                    if (cycle == 0)
                        sb.Append("timestamp, B6  ,B5  ,B4  ,B3  ,B2  ,B1  ,1st ,2nd ,3rd ,4th ,5th ,6th ,7th ,8th ,9th\n");//header
                    //sb.Append("timestamp, B6  ,B5  ,B4  ,B3  ,B2  ,B1  ,1st ,2nd ,3rd ,4th ,5th ,6th ,7th ,8th ,9th , ev0postion, ev1postion, ev2postion, ev3postion\n");
                    sb.Append(cycle);
                    sb.Append(',');
                    foreach (var floor in floors)
                    {
                        sb.Append(floor.AllUser);
                        sb.Append(", ");
                    }
                    foreach (var ev in elevators)
                    {
                        sb.Append(ev.currnetfloor);
                        sb.Append(", ");
                    }
                    sb.Remove(sb.Length - 2, 2);
                    sb.Append("\n");
                    string fileLine = sb.ToString();
                    byte[] info = new UTF8Encoding(true).GetBytes(fileLine);
                    fs.Write(info, 0, info.Length);
                    //personcount.Add(floors[6].AllUser);
                }
                Console.WriteLine(Path.GetFullPath(writeFileName) + "   Write Complete!");
            }

            /*
            personcountdelta.Add(0);
            for (int i = 1; i < personcount.Count; i++)
            {
                personcountdelta.Add(personcount[i] - personcount[i - 1]);
            }
            using (FileStream fs = File.Create("./sim2.csv"))
            {
                StringBuilder sb = new StringBuilder();
                sb.Append("timestamp, 7th floor, person delta\n");
                for (int i = 0; i < personcount.Count; i++)
                {
                    sb.Append(i + "," + personcount[i] + "," + personcountdelta[i] + "\n");
                }
                string fileLine = sb.ToString();
                byte[] info = new UTF8Encoding(true).GetBytes(fileLine);
                fs.Write(info, 0, info.Length);
            }*/
        }
    }
}
