using System;
using System.Collections.Generic;
using System.Dynamic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace Elevatorsim
{
    public enum DayOfWeek
    {
        //sun = 0,
        mon = 1,
        tue = 2,
        wed = 3,
        thu = 4,
        fri = 5,
        //sat = 6
    }
    class EvWeeklyData
    {
        List<Dictionary<string, string>> rows = new List<Dictionary<string, string>>();
        public EvWeeklyData(string filename)
        {
            var csvlines = File.ReadLines(filename).Select(line => line.Split(',')).ToList();
            for (int i = 1; i < csvlines.Count(); i++)
            { 
                rows.Add( csvlines[0].Zip(csvlines[i], (k, v) => new { k, v })
                    .ToDictionary(x => x.k, x => x.v)// from: https://stackoverflow.com/questions/4038978/
                    );
            }
        }
        public float this[int floor, int time, DayOfWeek w, char evcode]
        {
            get {
                if (time < 9)
                    return 0;

                var d = rows.ElementAt(floor);
                string s = d[w.ToString() + time + evcode];
                
                if (float.TryParse(s, out float result))
                    return result;
                else return 0;
            }
        }

    }
}
