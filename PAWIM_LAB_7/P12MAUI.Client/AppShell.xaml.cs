﻿namespace P12MAUI.Client
{
    public partial class AppShell : Shell
    {
        public AppShell()
        {
            InitializeComponent();

            Routing.RegisterRoute(nameof(ComputerDetailsView), typeof(ComputerDetailsView));
         
        }
    }
}