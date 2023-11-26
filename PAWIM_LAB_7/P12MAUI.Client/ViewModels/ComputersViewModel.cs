using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using P12MAUI.Client.Model;
using P12MAUI.Client.Services;
using P12MAUI.Client.Services.ComputersService;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace P12MAUI.Client.ViewModels
{
    public partial class ComputersViewModel : ObservableObject
    {
        private readonly IComputersService _computersService;
        private readonly ComputerDetailsView _computerDetailsView;
        private readonly IMessageDialogService _messageDialogService;
        private readonly IConnectivity _connectivity;

        public ObservableCollection<Computer> AllComputers {  get; set; }

        [ObservableProperty]
        private Computer selectedComputer;

        public ComputersViewModel(IComputersService computersService, ComputerDetailsView computerDetailsView, IMessageDialogService messageDialogService,
             IConnectivity connectivity) 
        {
            _messageDialogService = messageDialogService;
            _computerDetailsView = computerDetailsView;
            _computersService = computersService;
            _connectivity = connectivity;
            AllComputers = new ObservableCollection<Computer>();
            GetComputers();
        }

        public async Task GetComputers()
        {
            AllComputers.Clear();

            if (_connectivity.NetworkAccess != NetworkAccess.Internet)
            {
                _messageDialogService.ShowMessage("Internet not available!");
                return;
            }

            var computersResult = await _computersService.GetAllComputersAsync();
            foreach (var p in computersResult)
            {
                AllComputers.Add(p);
            }
        }

        [RelayCommand]
        public async Task DeleteAll()
        {

            if (_connectivity.NetworkAccess != NetworkAccess.Internet)
            {
                _messageDialogService.ShowMessage("Internet not available!");
                return;
            }

            await _computersService.DeleteAllComputers();
            GetComputers();
        }

        [RelayCommand]
        public async Task ShowDetails(Computer computer)
        {
            if (_connectivity.NetworkAccess != NetworkAccess.Internet)
            {
                _messageDialogService.ShowMessage("Internet not available!");
                return;
            }

            await Shell.Current.GoToAsync(nameof(ComputerDetailsView), true, new Dictionary<string, object>
            {
                {"Computer", computer },
                {nameof(ComputersViewModel), this }
            });
            SelectedComputer = computer;
        }

        [RelayCommand]
        public async Task New()
        {
            if (_connectivity.NetworkAccess != NetworkAccess.Internet)
            {
                _messageDialogService.ShowMessage("Internet not available!");
                return;
            }

            SelectedComputer = new Computer();
            await Shell.Current.GoToAsync(nameof(ComputerDetailsView), true, new Dictionary<string, object>
            {
                {"Computer", SelectedComputer },
                {nameof(ComputersViewModel), this }
            });
        }

    }
}
