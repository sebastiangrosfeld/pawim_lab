package com.example.desktop_app;

public class Constants {

    public final static String COMPUTERS_ENDPOINT = "http://localhost:8080/apiServer/computers";
    public final static String RAM_ENDPOINT = "http://localhost:8080/apiServer/rams";
    public final static String GPU_ENDPOINT = "http://localhost:8080/apiServer/gpus";
    public final static String LOGIN_ENDPOINT = "http://localhost:8080/apiServer/authorization/login";
    public final static String REGISTER_ENDPOINT = "http://localhost:8080/apiServer/authorization/register";
    public final static String CHANGE_PASSWORD_ENDPOINT = "http://localhost:8080/apiServer/authorization/change-password";
    public final static int ROWS_PER_PAGE = 10;
    public static final String BASE_URL = "http://dataservice.accuweather.com";
    public static final String API_KEY = "?apikey=DV5UlFSLiulMCerch1pKvoY0wncL4Mi2";
    public static final String LANGUAGE = "&language=pl";
    public static final String AUTOCOMPLETE_ENDPOINT = "/locations/v1/cities/autocomplete";
    public static final String CURRENT_CONDITIONS_ENDPOINT = "/currentconditions/v1/";
    public static final String ALARMS_FROM_FIVE_DAYS_ENDPOINT = "/alarms/v1/5day/";
    public static final String DAILY_FORECAST_ENDPOINT = "/forecasts/v1/daily/1day/";
    public static final String FIVE_DAYS_FORECAST_ENDPOINT = "/forecasts/v1/daily/5day/";
    public static final String HOURLY_FORECAST_ENDPOINT = "/forecasts/v1/hourly/12hour/";
    public static final String DETAILS_FALSE = "&details=false";
    public static final String METRIC_TRUE = "&metric=true";
}
