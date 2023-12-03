package com.example.desktop_app.service;

import com.example.desktop_app.model.AuthCredentials;
import com.example.desktop_app.model.ChangePassword;
import com.example.desktop_app.model.Jwt;

public interface AuthService {

    Jwt login(AuthCredentials login);

    void register(AuthCredentials register);

    void changePassword(ChangePassword changePassword);
}
