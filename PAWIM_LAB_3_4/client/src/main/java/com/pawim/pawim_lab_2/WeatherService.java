package com.pawim.pawim_lab_2;


import com.fasterxml.jackson.databind.JsonNode;
import com.pawim.pawim_lab_2.models.*;

import java.util.ArrayList;
import java.util.List;

import static com.pawim.pawim_lab_2.Constants.*;
import static com.pawim.pawim_lab_2.JsonNodeService.getJsonResponseBody;

public class WeatherService implements WeatherAppService{



    @Override
    public Location getLocationKeyByCityName(String cityName) {
        String url = BASE_URL + AUTOCOMPLETE_ENDPOINT + API_KEY + "&q=" + cityName + LANGUAGE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        return new Location(response.get(0).get("Key").asText());
    }

    @Override
    public Temperature getTemperatureByLocationKey(Location location) {
        String url = BASE_URL + CURRENT_CONDITIONS_ENDPOINT + location.key() + API_KEY + LANGUAGE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        return new Temperature(response.get(0).get("Temperature").get("Metric").get("Value").asText() , "'C");
    }

    @Override
    public Alarm getLastWeatherAlarmFrom5DaysByLocationKey(Location location) {
        String url = BASE_URL + ALARMS_FROM_FIVE_DAYS_ENDPOINT + location.key() + API_KEY + LANGUAGE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        if(response.get(0) == null)
            return new Alarm("Brak alarmu", " ");
        String alarmType = response.get(0).get("Alarms").get(0).get("AlarmType").asText();
        String date = response.get(0).get("Date").asText().substring(0,10);
        return new Alarm(alarmType, date);
    }

    @Override
    public List<TemperatureWithDate> getTemperaturesFrom24hByLocationKey(Location location) {
        String url = BASE_URL + CURRENT_CONDITIONS_ENDPOINT + location.key() + "/historical/24" + API_KEY + LANGUAGE + DETAILS_FALSE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        int i = 0;
        List<TemperatureWithDate> list = new ArrayList<>();
        while(response.get(i) != null) {
            Temperature temperature = new Temperature(response.get(i).get("Temperature").get("Metric").get("Value").asText(), "'C");
            String date = response.get(0).get("LocalObservationDateTime").asText().substring(11,19);
            i++;
            list.add(new TemperatureWithDate(temperature,date));
        }
        return list;
    }

    @Override
    public Forecast getDailyForecastByKeyLocation(Location location) {
        String url = BASE_URL + DAILY_FORECAST_ENDPOINT + location.key() + API_KEY + LANGUAGE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        return new Forecast("Dla " +
                response.get("Headline").get("EffectiveDate").asText().substring(0, 10) +
                "\n" +
                response.get("Headline").get("Text").asText());
    }

    @Override
    public List<TemperatureWithDate> getMaxTemperatureForNext5DaysByLocationKey(Location location) {
        String url = BASE_URL + FIVE_DAYS_FORECAST_ENDPOINT + location.key() + API_KEY + LANGUAGE + METRIC_TRUE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        int i = 0;
        List<TemperatureWithDate> list = new ArrayList<>();
        while(response.get("DailyForecasts").get(i) != null) {
            Temperature temperature = new Temperature(
                    response.get("DailyForecasts").get(i).get("Temperature").get("Maximum").get("Value").asText(),
                    "'C");
            String date = response.get("DailyForecasts").get(i).get("Date").asText().substring(0,10);
            i++;
            list.add(new TemperatureWithDate(temperature, date));
        }
        return list;
    }

    @Override
    public List<TemperatureWithDate> getTemperatureFromNext12HoursByLocationKey(Location location) {
        String url = BASE_URL + HOURLY_FORECAST_ENDPOINT + location.key() + API_KEY + LANGUAGE + METRIC_TRUE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        int i = 0;
        List<TemperatureWithDate> list = new ArrayList<>();
        while(response.get(i) != null) {
            Temperature temperature = new Temperature(
                    response.get(i).get("Temperature").get("Value").asText(),
                    "'C"
            );
            String date = response.get(i).get("DateTime").asText().substring(11,19);
            i++;
            list.add(new TemperatureWithDate(temperature, date));
        }
        return list;
    }
}
