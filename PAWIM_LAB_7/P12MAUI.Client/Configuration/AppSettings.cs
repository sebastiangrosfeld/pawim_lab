using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace P12MAUI.Client.Configuration
{
    public class AppSettings
    {
        public string DefaultLanguage { get; set; }
        public string SpeechApiKey { get; set; }
        public string BaseAPIUrl { get; set; }
        public SpeechSettings SpeechSettings { get; set; }
        public ComputersEndpoints ComputersEndpoints { get; set; }
    }
}
