package com.pawim.pawim_lab_2;

import com.pawim.pawim_lab_2.models.*;

import java.util.List;

public interface WeatherAppService {

    Location getLocationKeyByCityName(String cityName);

    Temperature getTemperatureByLocationKey(Location location);

    Alarm getLastWeatherAlarmFrom5DaysByLocationKey(Location location);

    List<TemperatureWithDate> getTemperaturesFrom24hByLocationKey(Location location);

    Forecast getDailyForecastByKeyLocation(Location location);

    List<TemperatureWithDate> getMaxTemperatureForNext5DaysByLocationKey(Location location);

    List<TemperatureWithDate> getTemperatureFromNext12HoursByLocationKey(Location location);

}
