package com.pawim.pawim_lab_2;

import com.google.inject.AbstractModule;

public class AppWeatherModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(WeatherAppService.class).to(WeatherService.class);
    }
}
