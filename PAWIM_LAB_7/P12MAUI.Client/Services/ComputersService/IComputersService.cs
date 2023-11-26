using P12MAUI.Client.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace P12MAUI.Client.Services.ComputersService
{
    public interface IComputersService
    {
        Task<List<Computer>> GetAllComputersAsync();

        Task<Computer> GetComputer(string computerName);

        Task<Computer> UpdateComputer (string computerName, Computer computer);

        Task<bool> DeleteComputerAsync (string computerName);

        Task<Computer> CreateComputer(Computer computer);

        Task<bool> DeleteAllComputers();
    }
}
