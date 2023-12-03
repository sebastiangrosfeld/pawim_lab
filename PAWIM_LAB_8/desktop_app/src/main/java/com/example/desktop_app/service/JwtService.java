package com.example.desktop_app.service;

public interface JwtService {

    void saveToken(String jwtToken);

    String getJwtToken();

    void removeToken();
}
