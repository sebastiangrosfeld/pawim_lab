package com.example.desktop_app;

import com.example.desktop_app.module.AppModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public enum ApplicationViews {

    WEATHER_APP_VIEW("weatherApp-view.fxml"),
    COMPUTER_APP_VIEW("computerApp-view.fxml"),
    CREATE_EDIT_VIEW("computerCreateEdit-view.fxml"),
    LOGIN_VIEW("login-view.fxml"),
    REGISTER_VIEW("register-view.fxml"),
    UN_AUTH_VIEW("unauthorized-view.fxml"),
    CHANGE_PASSWORD("change_password-view.fxml");

    private static final int WIDTH = 920;
    private static final int HEIGHT = 640;
    private static final Injector APP_INJECTOR = Guice.createInjector(new AppModule());

    private final String fxmlFile;

    ApplicationViews(String fxmlFile) {
        this.fxmlFile = fxmlFile;
    }

    public void loadScene(Stage stage) throws Exception {
        try {
            final var scene = loadViewResource();
            stage.setScene(scene);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

//    public <T> void loadScene(Stage stage, T payload) {
//        try {
//            final var scene = loadViewResource(payload);
//            stage.setScene(scene);
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }

    private Scene loadViewResource() throws IOException {
        final var viewResource = getViewResource();
        final var fxmlLoader = new FXMLLoader(viewResource);
        fxmlLoader.setControllerFactory(APP_INJECTOR::getInstance);
        return new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
    }

//    private <T> Scene loadViewResource(T payload) throws IOException {
//        final var viewResource = getViewResource();
//        final var fxmlLoader = new FXMLLoader(viewResource);
//        fxmlLoader.setControllerFactory(APP_INJECTOR::getInstance);
//        final Parent root = fxmlLoader.load();
//        final PayloadViewModel<T> viewModel = fxmlLoader.getController();
//        viewModel.processPayload(payload);
//        return new Scene(root, WIDTH, HEIGHT);
//    }

    public URL getViewResource() {
        return ApplicationViews.class.getResource(fxmlFile);
    }
}
