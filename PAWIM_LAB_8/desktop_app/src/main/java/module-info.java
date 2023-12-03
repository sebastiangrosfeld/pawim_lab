module com.example.desktop_app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires java.net.http;
    requires com.google.guice;
    requires java.prefs;

    opens com.example.desktop_app to javafx.fxml;
    opens com.example.desktop_app.viewModel to javafx.fxml;
    exports com.example.desktop_app;
    exports com.example.desktop_app.service.internal;
    exports com.example.desktop_app.viewModel;
    exports com.example.desktop_app.model;
}