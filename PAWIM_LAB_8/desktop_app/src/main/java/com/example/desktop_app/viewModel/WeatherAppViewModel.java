package com.example.desktop_app.viewModel;

import com.example.desktop_app.ApplicationViews;
import com.example.desktop_app.model.*;
import com.example.desktop_app.service.AuthService;
import com.example.desktop_app.service.WeatherService;
import com.example.desktop_app.view.AlarmView;
import com.example.desktop_app.view.ForecastView;
import com.example.desktop_app.view.TemperatureView;
import com.example.desktop_app.view.TemperaturesWithDateView;
import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class WeatherAppViewModel {

    private final WeatherService weatherAppService;
    private final AuthService authService;
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
    public WeatherAppViewModel(WeatherService weatherAppService, AuthService authService) {
        this.weatherAppService = weatherAppService;
        this.authService = authService;
    }

    @FXML
    public void onLogoutButtonClick(ActionEvent event) {

    }

    @FXML
    public void onComputerButtonClick(ActionEvent actionEvent) throws Exception {
        loadScene(ApplicationViews.UN_AUTH_VIEW, actionEvent);
    }

    private void loadScene(ApplicationViews view, ActionEvent event) throws Exception {
        final var source = (Node) event.getSource();
        final var stage = (Stage) source.getScene().getWindow();
        view.loadScene(stage);
    }
    @FXML
    protected void onTemperatureButtonClick() {
        if (isFieldCityNameIsNotEmpty(viewModel)) {
            Location location = weatherAppService.getLocationKeyByCityName(fxCityNameField.getText());
            TemperatureView temperatureView = getTemperatureModel(location);
            viewModel.setTextArea(temperatureView.temperature() + " " + temperatureView.metric());
        }
    }

    private TemperatureView getTemperatureModel(Location location) {
        Temperature temperature = weatherAppService.getTemperatureByLocationKey(location);
        return new TemperatureView(temperature.temperature(), temperature.metric());
    }

    @FXML
    protected void onLastWeatherAlarmButtonClick() {
        if (isFieldCityNameIsNotEmpty(viewModel)) {
            Location location = weatherAppService.getLocationKeyByCityName(fxCityNameField.getText());
            AlarmView alarmViewModel = getAlarmModel(location);
            viewModel.setTextArea(alarmViewModel.alarmCommunicate() + " dla " + alarmViewModel.date());
        }
    }

    private AlarmView getAlarmModel(Location location) {
        Alarm alarm = weatherAppService.getLastWeatherAlarmFrom5DaysByLocationKey(location);
        return new AlarmView(alarm.alarmCommunicate(), alarm.date());
    }

    @FXML
    protected void onTemperaturesFrom24HButtonClick() {
        if (isFieldCityNameIsNotEmpty(viewModel)) {
            Location location = weatherAppService.getLocationKeyByCityName(fxCityNameField.getText());
            TemperaturesWithDateView temperatures = getTemperatureFrom24hWithDateModels(location);
            viewModel.setTextArea(getListedTemperaturesWithDate(temperatures));
        }
    }

    private TemperaturesWithDateView getTemperatureFrom24hWithDateModels(Location location) {
        List<TemperatureWithDate> temperatureWithDates = weatherAppService.getTemperaturesFrom24hByLocationKey(location);
        return new TemperaturesWithDateView(temperatureWithDates);
    }

    @FXML
    protected void onDailyForecastButtonClick() {
        if (isFieldCityNameIsNotEmpty(viewModel)) {
            Location location = weatherAppService.getLocationKeyByCityName(fxCityNameField.getText());
            ForecastView forecastViewModel = getForecastModel(location);
            viewModel.setTextArea(forecastViewModel.communicate());
        }
    }

    private ForecastView getForecastModel(Location location) {
        Forecast forecast = weatherAppService.getDailyForecastByKeyLocation(location);
        return new ForecastView(forecast.communicate());
    }

    @FXML
    protected void onMaxTemperatureForNext5DaysButtonClick() {
        if (isFieldCityNameIsNotEmpty(viewModel)) {
            Location location = weatherAppService.getLocationKeyByCityName(fxCityNameField.getText());
            TemperaturesWithDateView temperaturesWithDateViewModel
                    = getMaxTemperatureForNext5DaysWithDateModels(location);
            viewModel.setTextArea(getListedTemperaturesWithDate(temperaturesWithDateViewModel));
        }
    }

    private TemperaturesWithDateView getMaxTemperatureForNext5DaysWithDateModels(Location location) {
        List<TemperatureWithDate> temperatureWithDates = weatherAppService
                .getMaxTemperatureForNext5DaysByLocationKey(location);
        return new TemperaturesWithDateView(temperatureWithDates);
    }

    @FXML
    protected void onTemperatureFromNext12HoursButtonClick() {
        if (isFieldCityNameIsNotEmpty(viewModel)) {
            Location location = weatherAppService.getLocationKeyByCityName(fxCityNameField.getText());
            TemperaturesWithDateView temperaturesWithDateViewModel
                    = getTemperatureFromNext12hWithDateModels(location);
            viewModel.setTextArea(getListedTemperaturesWithDate(temperaturesWithDateViewModel));
        }
    }

    private TemperaturesWithDateView getTemperatureFromNext12hWithDateModels(Location location) {
        List<TemperatureWithDate> temperatureWithDates = weatherAppService
                .getTemperatureFromNext12HoursByLocationKey(location);
        return new TemperaturesWithDateView(temperatureWithDates);
    }

    private String getListedTemperaturesWithDate(TemperaturesWithDateView temperaturesWithDateViewModel) {
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
