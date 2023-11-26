using P12MAUI.Client.ViewModels;

namespace P12MAUI.Client;

public partial class ComputerDetailsView : ContentPage
{
	public ComputerDetailsView(ComputerDetailsViewModel computerDetailsViewModel)
	{
		BindingContext = computerDetailsViewModel;
		InitializeComponent();
	}
}