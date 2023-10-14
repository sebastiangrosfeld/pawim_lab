package com.pawim.pawim_lab_1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class WeatherController {

    WeatherService weatherService = new WeatherService();

    @FXML
    private Label welcomeText;
    @FXML
    private TextArea fxTekst;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(weatherService.getTemperatureByLocationKey("226396"));
        fxTekst.setText(weatherService.getMaxTemperatureForNext5DaysByLocationKey("226396"));
    }
}