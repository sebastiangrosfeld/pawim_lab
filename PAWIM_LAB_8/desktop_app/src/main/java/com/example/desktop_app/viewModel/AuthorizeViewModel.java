package com.example.desktop_app.viewModel;

import com.example.desktop_app.ApplicationViews;
import com.example.desktop_app.model.AuthCredentials;
import com.example.desktop_app.model.ChangePassword;
import com.example.desktop_app.model.Jwt;
import com.example.desktop_app.service.AuthService;
import com.example.desktop_app.service.JwtService;
import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthorizeViewModel {

    private final AuthService authService;
    private final JwtService jwtService;

    @FXML
    private TextField fxUsernameField;
    @FXML
    private TextField fxPasswordField;
    @FXML
    private TextField fxOldPassword;
    @FXML
    private TextField fxNewPassword;

    @Inject
    public AuthorizeViewModel(AuthService authService, JwtService jwtService) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @FXML
    private void onGoLoginButtonClick(ActionEvent event) throws Exception {
        loadScene(ApplicationViews.LOGIN_VIEW, event);
    }

    @FXML
    private void onGoRegisterButtonClick(ActionEvent event) throws Exception {
        loadScene(ApplicationViews.REGISTER_VIEW, event);
    }

    @FXML
    private void onLoginButtonClick(ActionEvent event) throws Exception {
        String username = fxUsernameField.getText();
        String password = fxPasswordField.getText();
        AuthCredentials login = new AuthCredentials(username, password);
        Jwt jwt = authService.login(login);

        if (jwt == null) {
            return;
        }

        jwtService.saveToken(jwt.token());
        loadScene(ApplicationViews.COMPUTER_APP_VIEW, event);
    }

    @FXML
    private void onRegisterButtonClick(ActionEvent event) throws Exception {
        String username = fxUsernameField.getText();
        String password = fxPasswordField.getText();
        AuthCredentials register = new AuthCredentials(username, password);
        authService.register(register);

        loadScene(ApplicationViews.REGISTER_VIEW, event);
    }

    @FXML
    private void onChangePasswordButtonClick(ActionEvent event) throws Exception {
        String username = fxUsernameField.getText();
        String oldPassword = fxOldPassword.getText();
        String newPassword = fxNewPassword.getText();
        ChangePassword change = new ChangePassword(username, oldPassword, newPassword);
        authService.changePassword(change);

        loadScene(ApplicationViews.LOGIN_VIEW, event);
    }

    @FXML
    public void onGoBackButtonClick(ActionEvent event) throws Exception {
        loadScene(ApplicationViews.UN_AUTH_VIEW, event);
    }

    @FXML
    public void onGoWeatherButtonClick(ActionEvent event) throws Exception {
        loadScene(ApplicationViews.WEATHER_APP_VIEW, event);
    }

    private void loadScene(ApplicationViews view, ActionEvent event) throws Exception {
        final var source = (Node) event.getSource();
        final var stage = (Stage) source.getScene().getWindow();
        view.loadScene(stage);
    }
}
