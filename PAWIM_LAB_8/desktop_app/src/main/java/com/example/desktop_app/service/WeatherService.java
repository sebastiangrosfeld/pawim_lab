package com.example.desktop_app.service;

import com.example.desktop_app.model.*;

import java.util.List;

public interface WeatherService {

    Location getLocationKeyByCityName(String cityName);

    Temperature getTemperatureByLocationKey(Location location);

    Alarm getLastWeatherAlarmFrom5DaysByLocationKey(Location location);

    List<TemperatureWithDate> getTemperaturesFrom24hByLocationKey(Location location);

    Forecast getDailyForecastByKeyLocation(Location location);

    List<TemperatureWithDate> getMaxTemperatureForNext5DaysByLocationKey(Location location);

    List<TemperatureWithDate> getTemperatureFromNext12HoursByLocationKey(Location location);
}
