package com.pawim.pawim_lab_2;

import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import com.google.inject.Guice;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WeatherApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApplication.class.getResource("app-view.fxml"));
        makeDependencyInjection(fxmlLoader);
        Scene scene = new Scene(fxmlLoader.load(), 620, 540);
        stage.setTitle("App Weather Application v2");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void makeDependencyInjection(FXMLLoader fxmlLoader) {
        AppWeatherModule appWeatherModule = new AppWeatherModule();
        Injector injector = Guice.createInjector(appWeatherModule);
        fxmlLoader.setControllerFactory(injector::getInstance);
    }
}