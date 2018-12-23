namespace Elevatorsim
{
    class Elevator
    {
        public int passengerCount;
        public int maxPassengers;
        public int currnetfloor;
        public int minFloor = 1;
        public Direction going = Direction.up;
        public Action action = Action.loading;
        public float waiting = 0;
        public enum Action
        {
            loading,going,idle
        }
        public enum Direction
        {
            up, down
        }
        public enum StoppingFloors
        {
            oodOnlyfrom1st, evenOnlyfrom1st,
            oodOnlyfromb3, evenOnlyfromb3,
            oodOnlyfromb6, evenOnlyfromb6,
        }
        public string FloorToString()
        {
            if (currnetfloor == 1)
                return "1st";
            else if (currnetfloor == 2)
                return "2nd";
            else if (currnetfloor == 3)
                return "3rd";
            else if (currnetfloor >= 4)
                return currnetfloor + "th";
            else
                return "B" + (currnetfloor - 1) + ' ';
        }
        public string PrintFloorInfo(int floor)
        {
            if (currnetfloor == floor)
            {
                if (passengerCount == maxPassengers)
                    return "MX";
                else if (passengerCount >= 10)
                    return passengerCount.ToString();
                else if (passengerCount >= 0)
                    return passengerCount + " ";
            }
            return "  ";
        }
        public string goingData(int floor)//down side
        {
            if (floor == currnetfloor + (going == Direction.down ? 0 : 1))
            {
                if (action == Action.going)
                {
                    return going == Direction.down ? "vv" : "^^";
                }
                else 
                {
                    return going == Direction.down ? "-v" : "-^";
                }
            }
            else return "--";
        }
        public int LoadUser(int ridingpassenger)
        {
            int canride = maxPassengers - passengerCount;
            if (canride < ridingpassenger)
            {
                passengerCount = maxPassengers;
                return canride;
            }
            else
            {
                passengerCount += ridingpassenger;
                return ridingpassenger;
            }
        }

        public int UnloadUsers(float howMuch)
        {
            int unloading = (int)(passengerCount * howMuch);
            passengerCount -= unloading;
            return unloading;
        }
    }
}
