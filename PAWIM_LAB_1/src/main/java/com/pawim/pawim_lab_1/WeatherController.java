package com.pawim.pawim_lab_1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Objects;

public class WeatherController {

    WeatherService weatherService = new WeatherService();

    @FXML
    private TextArea fxText;
    @FXML
    private TextField fxCityNameField;

    @FXML
    protected void onTemperatureButtonClick() {
        if (fxCityNameField.getText() != null && !Objects.equals(fxCityNameField.getText(), "")) {
            String locationKey = weatherService.getLocationKeyByCityName(fxCityNameField.getText());
            fxText.setText(weatherService.getTemperatureByLocationKey(locationKey));
        }
    }

    @FXML
    protected void onLastWeatherAlarmButtonClick() {
        if (fxCityNameField.getText() != null && !Objects.equals(fxCityNameField.getText(), "")) {
            String locationKey = weatherService.getLocationKeyByCityName(fxCityNameField.getText());
            fxText.setText(weatherService.getLastWeatherAlarmFrom5DaysByLocationKey(locationKey));
        }
    }

    @FXML
    protected void onTemperaturesFrom24HButtonClick() {
        if (fxCityNameField.getText() != null && !Objects.equals(fxCityNameField.getText(), "")) {
            String locationKey = weatherService.getLocationKeyByCityName(fxCityNameField.getText());
            fxText.setText(weatherService.getTemperaturesFrom24hByLocationKey(locationKey));
        }
    }

    @FXML
    protected void onDailyForecastButtonClick() {
        if (fxCityNameField.getText() != null && !Objects.equals(fxCityNameField.getText(), "")) {
            String locationKey = weatherService.getLocationKeyByCityName(fxCityNameField.getText());
            fxText.setText(weatherService.getDailyForecastByKeyLocation(locationKey));
        }
    }

    @FXML
    protected void onMaxTemperatureForNext5DaysButtonClick() {
        if (fxCityNameField.getText() != null && !Objects.equals(fxCityNameField.getText(), "")) {
            String locationKey = weatherService.getLocationKeyByCityName(fxCityNameField.getText());
            fxText.setText(weatherService.getMaxTemperatureForNext5DaysByLocationKey(locationKey));
        }
    }

    @FXML
    protected void onTemperatureFromNext12HoursButtonClick() {
        if (fxCityNameField.getText() != null && !Objects.equals(fxCityNameField.getText(), "")) {
            String locationKey = weatherService.getLocationKeyByCityName(fxCityNameField.getText());
            fxText.setText(weatherService.getTemperatureFromNext12HoursByLocationKey(locationKey));
        }
    }
}