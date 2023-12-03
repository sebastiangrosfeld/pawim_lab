package com.example.desktop_app;

import com.example.desktop_app.module.AppModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DesktopApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationViews.WEATHER_APP_VIEW.getViewResource());
        makeDependencyInjection(fxmlLoader);
        Scene scene = new Scene(fxmlLoader.load(), 620, 540);
        stage.setTitle("App Desktop Application v3");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void makeDependencyInjection(FXMLLoader fxmlLoader) {
        AppModule appModule = new AppModule();
        Injector injector = Guice.createInjector(appModule);
        fxmlLoader.setControllerFactory(injector::getInstance);
    }

}