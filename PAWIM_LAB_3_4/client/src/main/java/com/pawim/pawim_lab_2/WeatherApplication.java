package com.pawim.pawim_lab_2;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WeatherApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationViews.WEATHER_APP_VIEW.getViewResource());
        makeDependencyInjection(fxmlLoader);
        Scene scene = new Scene(fxmlLoader.load(), 620, 540);
        stage.setTitle("App Weather Application v3");
        stage.setScene(scene);
        stage.show();
//        stage.setTitle("App Weather Application v3");
//        ApplicationViews.WEATHER_APP_VIEW.loadScene(stage);
//        stage.show();
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