module com.pawim.pawim_lab_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.google.guice;


    opens com.pawim.pawim_lab_2 to javafx.fxml;
    exports com.pawim.pawim_lab_2;
}