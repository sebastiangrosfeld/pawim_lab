using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace P12MAUI.Client.Model
{
    public class Ram
    {
        public string Name { get; set; }
        public int RamCapacity { get; set; }
        public int MemoryRate { get; set; }

        // Konstruktor
        public Ram(string name, int ramCapacity, int memoryRate)
        {
            this.Name = name;
            this.RamCapacity = ramCapacity;
            this.MemoryRate = memoryRate;
        }
    }
}
