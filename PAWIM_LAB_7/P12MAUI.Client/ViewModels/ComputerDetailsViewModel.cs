using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using P12MAUI.Client.Model;
using P12MAUI.Client.Services;
using P12MAUI.Client.Services.ComputersService;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace P12MAUI.Client.ViewModels
{
    [QueryProperty(nameof(Computer), nameof(Computer))]
    [QueryProperty(nameof(ComputersViewModel), nameof(ComputersViewModel))]
    public partial class ComputerDetailsViewModel : ObservableObject
    {
        private readonly IMap _map;
        private readonly IMessageDialogService _messageDialogService;
        private readonly IGeolocation _geolocation;
        private readonly IComputersService _computersService;
        private ComputersViewModel _computerViewModel;

        public ComputerDetailsViewModel(IMap map, IMessageDialogService messageDialogService, IGeolocation geolocation, IComputersService computersService)
        {
            _map = map;
            _messageDialogService = messageDialogService;
            _geolocation = geolocation;
            _computersService = computersService;
        }

        [RelayCommand]
        public async Task GetLocation()
        {
            var location = await _geolocation.GetLastKnownLocationAsync();

            try
            {
                await _map.OpenAsync(52.23564245654427, 21.0112328246909, new MapLaunchOptions
                {
                    Name = "ALX",
                    NavigationMode = NavigationMode.None
                });
            }
            catch (Exception)
            {
                _messageDialogService.ShowMessage("Error opening map");
            }
        }

        public ComputersViewModel ComputersViewModel
        {
            get
            {
                return _computerViewModel;
            }
            set
            {
                _computerViewModel = value;
            }
        }

        [ObservableProperty]
        Computer computer;

        public async Task DeleteComputer()
        {
            await _computersService.DeleteComputerAsync(computer.Name);
            await _computerViewModel.GetComputers();
        }

        public async Task CreateComputer()
        {
            var newComputer = new Computer()
            {
                Name = computer.Name,
                Type = computer.Type,
                EnclosureName = computer.EnclosureName,
                CpuName = computer.CpuName,
                Ram = computer.Ram,
                HardDiskCapacity = computer.HardDiskCapacity,
                Gpu = computer.Gpu,
                PowerSupply = computer.PowerSupply,
                Price = computer.Price,
            };

            var result = await _computersService.CreateComputer(newComputer);
           
            await _computerViewModel.GetComputers();
        }

        public async Task UpdateComputer()
        {
            var computerToUpdate = new Computer()
            {
                Name = computer.Name,
                Type = computer.Type,
                EnclosureName = computer.EnclosureName,
                CpuName = computer.CpuName,
                Ram = computer.Ram,
                HardDiskCapacity = computer.HardDiskCapacity,
                Gpu = computer.Gpu,
                PowerSupply = computer.PowerSupply,
                Price = computer.Price,
            };

            await _computersService.UpdateComputer(computer.Name, computerToUpdate);
            await _computerViewModel.GetComputers();
        }

        [RelayCommand]
        public async Task Save()
        {
            if (computer.Name == "")
            {
                CreateComputer();
                await Shell.Current.GoToAsync("../", true);

            }
            else
            {
                UpdateComputer();
                await Shell.Current.GoToAsync("../", true);
            }

        }

        [RelayCommand]
        public async Task Delete()
        {
            DeleteComputer();
            await Shell.Current.GoToAsync("../", true);
        }
    }
}
