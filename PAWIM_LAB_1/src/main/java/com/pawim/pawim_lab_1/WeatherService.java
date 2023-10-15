package com.pawim.pawim_lab_1;


import com.fasterxml.jackson.databind.JsonNode;

import static com.pawim.pawim_lab_1.JsonNodeService.getJsonResponseBody;

public class WeatherService {

    private static final String BASE_URL = "http://dataservice.accuweather.com";
    private static final String API_KEY = "?apikey=DV5UlFSLiulMCerch1pKvoY0wncL4Mi2";
    private static final String LANGUAGE = "&language=pl";
    private static final String AUTOCOMPLETE_ENDPOINT = "/locations/v1/cities/autocomplete";
    private static final String CURRENT_CONDITIONS_ENDPOINT = "/currentconditions/v1/";
    private static final String ALARMS_FROM_FIVE_DAYS_ENDPOINT = "/alarms/v1/5day/";
    private static final String DAILY_FORECAST_ENDPOINT = "/forecasts/v1/daily/1day/";
    private static final String FIVE_DAYS_FORECAST_ENDPOINT = "/forecasts/v1/daily/5day/";
    private static final String HOURLY_FORECAST_ENDPOINT = "/forecasts/v1/hourly/12hour/";
    private static final String DETAILS_FALSE = "&details=false";
    private static final String METRIC_TRUE = "&metric=true";

    public String getLocationKeyByCityName(String cityName) {
        String url = BASE_URL + AUTOCOMPLETE_ENDPOINT + API_KEY + "&q=" + cityName + LANGUAGE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        return response.get(0).get("Key").asText();
    }

    public String getTemperatureByLocationKey(String locationKey) {
        String url = BASE_URL + CURRENT_CONDITIONS_ENDPOINT + locationKey + API_KEY + LANGUAGE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        return response.get(0).get("Temperature").get("Metric").get("Value").asText() + " 'C";
    }

    public String getLastWeatherAlarmFrom5DaysByLocationKey(String locationKey) {
        String url = BASE_URL + ALARMS_FROM_FIVE_DAYS_ENDPOINT + locationKey + API_KEY + LANGUAGE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        if(response.get(0) == null)
            return "Brak alarmu";
        String alarmType = response.get(0).get("Alarms").get(0).get("AlarmType").asText();
        String date = response.get(0).get("Date").asText().substring(0,10);
        return alarmType + " dla " + date;
    }

    public String getTemperaturesFrom24hByLocationKey(String locationKey) {
        String url = BASE_URL + CURRENT_CONDITIONS_ENDPOINT + locationKey + "/historical/24" + API_KEY + LANGUAGE + DETAILS_FALSE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        int i = 0;
        StringBuilder result = new StringBuilder();
        while(response.get(i) != null) {
            result.append(response.get(i).get("Temperature").get("Metric").get("Value").asText());
            result.append(" w czasie: ");
            result.append(response.get(0).get("LocalObservationDateTime").asText(), 11, 19);
            result.append("\n");
            i++;
        }
        return result.toString();
    }

    public String getDailyForecastByKeyLocation(String locationKey) {
        String url = BASE_URL + DAILY_FORECAST_ENDPOINT + locationKey + API_KEY + LANGUAGE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        return "Dla " +
                response.get("Headline").get("EffectiveDate").asText().substring(0, 10) +
                "\n" +
                response.get("Headline").get("Text").asText();
    }

    public String getMaxTemperatureForNext5DaysByLocationKey(String locationKey) {
        String url = BASE_URL + FIVE_DAYS_FORECAST_ENDPOINT + locationKey + API_KEY + LANGUAGE + METRIC_TRUE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        int i = 0;
        StringBuilder result = new StringBuilder();
        while(response.get("DailyForecasts").get(i) != null) {
            result.append(response.get("DailyForecasts").get(i).get("Temperature").get("Maximum").get("Value").asText());
            result.append(" 'C");
            result.append(" dla ");
            result.append(response.get("DailyForecasts").get(i).get("Date").asText(), 0, 10);
            result.append("\n");
            i++;
        }
        return result.toString();
    }

    public String getTemperatureFromNext12HoursByLocationKey(String locationKey) {
        String url = BASE_URL + HOURLY_FORECAST_ENDPOINT + locationKey + API_KEY + LANGUAGE + METRIC_TRUE;
        JsonNode response = getJsonResponseBody(url);
        assert response != null;
        int i = 0;
        StringBuilder result = new StringBuilder();
        while(response.get(i) != null) {
            result.append(response.get(i).get("Temperature").get("Value").asText());
            result.append(" 'C");
            result.append(" o ");
            result.append(response.get(i).get("DateTime").asText(), 11, 19);
            result.append("\n");
            i++;
        }
        return result.toString();
    }
}
