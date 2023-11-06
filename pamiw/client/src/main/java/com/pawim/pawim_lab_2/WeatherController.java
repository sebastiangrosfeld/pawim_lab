package com.pawim.pawim_lab_2;

import com.google.inject.Inject;
import com.pawim.pawim_lab_2.models.*;
import com.pawim.pawim_lab_2.views.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Objects;

public class WeatherController {

    private final WeatherAppService weatherAppService;
    private MainViewModel viewModel;

    @FXML
    private TextArea fxText;
    @FXML
    private TextField fxCityNameField;

    public void initialize() {
        viewModel = new MainViewModel();
        fxText.textProperty().bindBidirectional(viewModel.textAreaProperty());
        fxCityNameField.textProperty().bindBidirectional(viewModel.fieldTextAreaProperty());
    }

    @Inject
    public WeatherController(WeatherAppService weatherAppService) {
        this.weatherAppService = weatherAppService;
    }

    @FXML
    protected void onTemperatureButtonClick() {
        if (isFieldCityNameIsNotEmpty(viewModel)) {
            Location location = weatherAppService.getLocationKeyByCityName(fxCityNameField.getText());
            TemperatureViewModel temperatureView = getTemperatureModel(location);
            viewModel.setTextArea(temperatureView.temperature() + " " + temperatureView.metric());
        }
    }

    private TemperatureViewModel getTemperatureModel(Location location) {
        Temperature temperature = weatherAppService.getTemperatureByLocationKey(location);
        return new TemperatureViewModel(temperature.temperature(), temperature.metric());
    }

    @FXML
    protected void onLastWeatherAlarmButtonClick() {
        if (isFieldCityNameIsNotEmpty(viewModel)) {
            Location location = weatherAppService.getLocationKeyByCityName(fxCityNameField.getText());
            AlarmViewModel alarmViewModel = getAlarmModel(location);
            viewModel.setTextArea(alarmViewModel.alarmCommunicate() + " dla " + alarmViewModel.date());
        }
    }

    private AlarmViewModel getAlarmModel(Location location) {
        Alarm alarm = weatherAppService.getLastWeatherAlarmFrom5DaysByLocationKey(location);
        return new AlarmViewModel(alarm.alarmCommunicate(), alarm.date());
    }

    @FXML
    protected void onTemperaturesFrom24HButtonClick() {
        if (isFieldCityNameIsNotEmpty(viewModel)) {
            Location location = weatherAppService.getLocationKeyByCityName(fxCityNameField.getText());
            TemperaturesWithDateViewModel temperatures = getTemperatureFrom24hWithDateModels(location);
            viewModel.setTextArea(getListedTemperaturesWithDate(temperatures));
        }
    }

    private TemperaturesWithDateViewModel getTemperatureFrom24hWithDateModels(Location location) {
        List<TemperatureWithDate> temperatureWithDates = weatherAppService.getTemperaturesFrom24hByLocationKey(location);
        return new TemperaturesWithDateViewModel(temperatureWithDates);
    }

    @FXML
    protected void onDailyForecastButtonClick() {
        if (isFieldCityNameIsNotEmpty(viewModel)) {
            Location location = weatherAppService.getLocationKeyByCityName(fxCityNameField.getText());
            ForecastViewModel forecastViewModel = getForecastModel(location);
            viewModel.setTextArea(forecastViewModel.communicate());
        }
    }

    private ForecastViewModel getForecastModel(Location location) {
        Forecast forecast = weatherAppService.getDailyForecastByKeyLocation(location);
        return new ForecastViewModel(forecast.communicate());
    }

    @FXML
    protected void onMaxTemperatureForNext5DaysButtonClick() {
        if (isFieldCityNameIsNotEmpty(viewModel)) {
            Location location = weatherAppService.getLocationKeyByCityName(fxCityNameField.getText());
            TemperaturesWithDateViewModel temperaturesWithDateViewModel
                    = getMaxTemperatureForNext5DaysWithDateModels(location);
            viewModel.setTextArea(getListedTemperaturesWithDate(temperaturesWithDateViewModel));
        }
    }

    private TemperaturesWithDateViewModel getMaxTemperatureForNext5DaysWithDateModels(Location location) {
        List<TemperatureWithDate> temperatureWithDates = weatherAppService
                .getMaxTemperatureForNext5DaysByLocationKey(location);
        return new TemperaturesWithDateViewModel(temperatureWithDates);
    }

    @FXML
    protected void onTemperatureFromNext12HoursButtonClick() {
        if (isFieldCityNameIsNotEmpty(viewModel)) {
            Location location = weatherAppService.getLocationKeyByCityName(fxCityNameField.getText());
            TemperaturesWithDateViewModel temperaturesWithDateViewModel
                    = getTemperatureFromNext12hWithDateModels(location);
            viewModel.setTextArea(getListedTemperaturesWithDate(temperaturesWithDateViewModel));
        }
    }

    private TemperaturesWithDateViewModel getTemperatureFromNext12hWithDateModels(Location location) {
        List<TemperatureWithDate> temperatureWithDates = weatherAppService
                .getTemperatureFromNext12HoursByLocationKey(location);
        return new TemperaturesWithDateViewModel(temperatureWithDates);
    }

    private String getListedTemperaturesWithDate(TemperaturesWithDateViewModel temperaturesWithDateViewModel) {
        StringBuilder stringBuilder = new StringBuilder();
        for(TemperatureWithDate temperature : temperaturesWithDateViewModel.temperatures() ){
            stringBuilder.append(temperature.temperature().toString())
                    .append(" dla ")
                    .append(temperature.date())
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    private boolean isFieldCityNameIsNotEmpty(MainViewModel viewModel) {
        return viewModel.getFieldTextArea() != null && !Objects.equals(viewModel.getFieldTextArea(), "");
    }
}