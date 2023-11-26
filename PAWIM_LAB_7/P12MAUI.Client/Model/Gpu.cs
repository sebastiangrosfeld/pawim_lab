using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace P12MAUI.Client.Model
{
    public class Gpu
    {
        public string Name { get; set; }
        public int VideoRamCapacity { get; set; }

        // Konstruktor
        public Gpu(string name, int videoRamCapacity)
        {
            this.Name = name;
            this.VideoRamCapacity = videoRamCapacity;
        }
    }
}
