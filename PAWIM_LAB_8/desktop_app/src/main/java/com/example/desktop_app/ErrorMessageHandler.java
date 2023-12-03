package com.example.desktop_app;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.List;

public class ErrorMessageHandler {

    public static void handle(List<String> messages) {
        final var message = String.join("\n", messages);
        final var error = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        error.show();
    }
}
