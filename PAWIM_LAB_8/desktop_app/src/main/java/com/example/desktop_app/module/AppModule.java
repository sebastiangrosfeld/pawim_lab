package com.example.desktop_app.module;

import com.example.desktop_app.service.*;
import com.example.desktop_app.service.internal.*;
import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(WeatherService.class).to(WeatherServiceImpl.class);
        bind(ComputerService.class).to(ComputerServiceImpl.class);
        bind(GpuService.class).to(GpuServiceImpl.class);
        bind(RamService.class).to(RamServiceImpl.class);
        bind(AuthService.class).to(AuthServiceImpl.class);
        bind(JwtService.class).to(JwtServiceImpl.class);

    }
}
