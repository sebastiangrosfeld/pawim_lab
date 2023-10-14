package com.pawim.pawim_lab_1;


import static com.pawim.pawim_lab_1.JsonNodeService.getJsonResponseBody;

public class WeatherService {

    private static final String baseUrl = "http://dataservice.accuweather.com";
    private static final String apiKey = "?apikey=DV5UlFSLiulMCerch1pKvoY0wncL4Mi2";
    private static final String language = "&language=pl";
    private static final String autocompleteEndpoint = "/locations/v1/cities/autocomplete";
    private static final String currentConditionsEndpoint = "/currentconditions/v1/";
    private static final String alarmsFromFiveDaysEndpoint = "/alarms/v1/5day/";
    private static final String dailyForecastEndpoint = "/forecasts/v1/daily/1day/";
    private static final String fiveDaysForecastEndpoint = "/forecasts/v1/daily/5day/";
    private static final String hourlyForecastEndpoint = "/forecasts/v1/hourly/12hour/";
    private static final String details = "&details=false";
    private static final String metric = "&metric=true";

    public String getLocationKeyByCityName(String cityName) {
        var url = baseUrl + autocompleteEndpoint + apiKey + "&q=" + cityName + language;
        var response = getJsonResponseBody(url);
        assert response != null;
        return response.get(0).get("Key").asText();
    }

    public String getTemperatureByLocationKey(String locationKey) {
        var url = baseUrl + currentConditionsEndpoint + locationKey + apiKey + language;
        var response = getJsonResponseBody(url);
        assert response != null;
        return response.get(0).get("Temperature").get("Metric").get("Value").asText() + " 'C";
    }

    public String getLastWeatherAlarmFrom5DaysByLocationKey(String locationKey) {
        var url = baseUrl + alarmsFromFiveDaysEndpoint + locationKey + apiKey + language;
        var response = getJsonResponseBody(url);
        assert response != null;
        if(response.get(0) == null)
            return "Brak alarmu";
        String alarmType = response.get(0).get("Alarms").get(0).get("AlarmType").asText();
        String date = response.get(0).get("Date").asText().substring(0,10);
        return alarmType + " at " + date;
    }

    public String getTemperaturesFrom24hByLocationKey(String locationKey) {
        var url = baseUrl + currentConditionsEndpoint + locationKey + "/historical/24" + apiKey + language + details;
        var response = getJsonResponseBody(url);
        assert response != null;
        int i = 0;
        StringBuilder result = new StringBuilder();
        while(response.get(i) != null) {
            result.append(response.get(i).get("Temperature").get("Metric").get("Value").asText());
            result.append(" at time: ");
            result.append(response.get(0).get("LocalObservationDateTime").asText(), 11, 19);
            result.append("\n");
            i++;
        }
        return result.toString();
    }

    public String getDailyForecastByKeyLocation(String locationKey) {
        var url = baseUrl + dailyForecastEndpoint + locationKey + apiKey + language;
        var response = getJsonResponseBody(url);
        assert response != null;
        return "For " +
                response.get("Headline").get("EffectiveDate").asText().substring(0, 10) +
                "\n" +
                response.get("Headline").get("Text").asText();
    }

    public String getMaxTemperatureForNext5DaysByLocationKey(String locationKey) {
        var url = baseUrl + fiveDaysForecastEndpoint + locationKey + apiKey + language + metric;
        var response = getJsonResponseBody(url);
        assert response != null;
        int i = 0;
        System.out.println(response);
        StringBuilder result = new StringBuilder();
        while(response.get("DailyForecasts").get(i) != null) {
            result.append(response.get("DailyForecasts").get(i).get("Temperature").get("Maximum").get("Value").asText());
            result.append(" 'C");
            result.append(" at ");
            result.append(response.get("DailyForecasts").get(i).get("Date").asText(), 0, 10);
            result.append("\n");
            i++;
        }
        return result.toString();
    }

    public String getTemperatureFromNext12HoursByLocationKey(String locationKey) {
        var url = baseUrl + hourlyForecastEndpoint + locationKey + apiKey + language + metric;
        var response = getJsonResponseBody(url);
        assert response != null;
        int i = 0;
        StringBuilder result = new StringBuilder();
        while(response.get(i) != null) {
            result.append(response.get(i).get("Temperature").get("Value").asText());
            result.append(" 'C");
            result.append(" at ");
            result.append(response.get(i).get("DateTime").asText(), 11, 19);
            result.append("\n");
            i++;
        }
        return result.toString();
    }
}
