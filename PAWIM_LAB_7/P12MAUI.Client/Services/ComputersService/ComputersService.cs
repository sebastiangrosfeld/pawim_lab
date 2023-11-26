using Microsoft.Extensions.Options;
using Microsoft.VisualBasic;
using P12MAUI.Client.Configuration;
using P12MAUI.Client.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http.Json;
using System.Text;
using System.Text.Json.Serialization;
using System.Text.Json;
using System.Threading.Tasks;

namespace P12MAUI.Client.Services.ComputersService
{
    public class ComputersService : IComputersService
    {
        private readonly HttpClient _httpClient;
        private readonly AppSettings _appSettings;
        public ComputersService(HttpClient httpClient, IOptions<AppSettings> appSettings)
        {
            _httpClient = httpClient;
            _appSettings = appSettings.Value;
        }

        public async Task<Computer> CreateComputer(Computer computer)
        {
            await _httpClient.PostAsJsonAsync(_appSettings.BaseAPIUrl + _appSettings.ComputersEndpoints.AddComputerEndpoint, computer);
            return computer;
        }

        public async Task<bool> DeleteAllComputers()
        {
            await _httpClient.DeleteAsync(_appSettings.BaseAPIUrl + _appSettings.ComputersEndpoints.DeleteAllComputersEndpoint);
            return true;
        }

        public async Task<bool> DeleteComputerAsync(string computerName)
        {
            await _httpClient.DeleteAsync(_appSettings.BaseAPIUrl + String.Format(_appSettings.ComputersEndpoints.DeleteComputerEndpoint, computerName));
            return true;
        }

        public async Task<List<Computer>> GetAllComputersAsync()
        {
            var computers = await _httpClient.GetFromJsonAsync<List<Computer>>(_appSettings.BaseAPIUrl + _appSettings.ComputersEndpoints.GetAllComputersEndpoint);
            return computers;
        }

        public async Task<Computer> GetComputer(string computerName)
        {
            var computer = await _httpClient.GetFromJsonAsync<Computer>(_appSettings.BaseAPIUrl + String.Format( _appSettings.ComputersEndpoints.GetComputerEndpoint, computerName));
            return computer;
        }

        public async Task<Computer> UpdateComputer(string computerName, Computer computer)
        {
            await _httpClient.PutAsJsonAsync(_appSettings.BaseAPIUrl + String.Format(_appSettings.ComputersEndpoints.UpdateComputerEndpoint, computerName), computer);
            return computer;
        }
    }
}
