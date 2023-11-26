using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.Maui.Controls;
using P12MAUI.Client.Configuration;
using P12MAUI.Client.MessageBox;
using P12MAUI.Client.ViewModels;
using System.Diagnostics;
using P12MAUI.Client.Services;
using P12MAUI.Client.Services.ComputersService;

namespace P12MAUI.Client
{
    public static class MauiProgram
    {
        public static MauiApp CreateMauiApp()
        {
            var builder = MauiApp.CreateBuilder();
            builder
                .UseMauiApp<App>()
                .ConfigureFonts(fonts =>
                {
                    fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
                    fonts.AddFont("OpenSans-Semibold.ttf", "OpenSansSemibold");
                });

#if DEBUG
            builder.Logging.AddDebug();
#endif


            ConfigureServices(builder.Services);
            return builder.Build();
        }


        private static void ConfigureServices(IServiceCollection services)
        {
            var appSettingsSection = ConfigureAppSettings(services);
            ConfigureAppServices(services, appSettingsSection);
            ConfigureViewModels(services);
            ConfigureViews(services);
            ConfigureHttpClients(services, appSettingsSection);
        }

        private static AppSettings ConfigureAppSettings(IServiceCollection services)
        {
           

            string workingDirectory = AppContext.BaseDirectory;
            string projectDir = Directory.GetParent(workingDirectory).Parent.Parent.Parent.Parent.Parent.FullName;

            var builder = new ConfigurationBuilder()
              .AddUserSecrets<MauiApp>()
              .SetBasePath(projectDir)
              .AddJsonFile("appsettings.json");
            IConfiguration _configuration = builder.Build();

            IConfiguration configuration = new ConfigurationBuilder()
                .SetBasePath(projectDir)
                .AddJsonFile("appsettings.json")
                .Build();

            var appSettings = _configuration.GetSection(nameof(AppSettings));
            var appSettingsSection = appSettings.Get<AppSettings>();

            
           



            services.Configure<AppSettings>(appSettings);

            

            return appSettingsSection;
        }

        private static void ConfigureAppServices(IServiceCollection services, AppSettings appSettings)
        {
            services.AddSingleton<IConnectivity>(Connectivity.Current);
            services.AddSingleton<IGeolocation>(Geolocation.Default);
            services.AddSingleton<IMap>(Map.Default);

            // konfiguracja serwisów 
            services.AddSingleton<IComputersService, ComputersService>();
            services.AddSingleton<IMessageDialogService, MauiMessageDialogService>();
          
        }

        private static void ConfigureViewModels(IServiceCollection services)
        {

            // konfiguracja viewModeli 
           
        
            services.AddSingleton<ComputersViewModel>();
            services.AddTransient<ComputerDetailsViewModel>();
          
            // services.AddSingleton<BaseViewModel,MainViewModelV3>();
        }

        private static void ConfigureViews(IServiceCollection services)
        {
            // konfiguracja okienek 
            services.AddSingleton<MainPage>();    
            services.AddTransient<ComputerDetailsView>();
        }

        private static void ConfigureHttpClients(IServiceCollection services, AppSettings appSettingsSection)
        {
            var uriBuilder = new UriBuilder(appSettingsSection.BaseAPIUrl)
            {
            
                Path = appSettingsSection.ComputersEndpoints.Base_url,
            };
            //Microsoft.Extensions.Http
            services.AddHttpClient<IComputersService, ComputersService>(client => client.BaseAddress = uriBuilder.Uri);
        }
    }
}