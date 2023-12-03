package com.example.desktop_app.service.internal;

import com.example.desktop_app.ErrorMessageHandler;
import com.example.desktop_app.model.*;
import com.example.desktop_app.service.AuthService;
import com.example.desktop_app.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.example.desktop_app.Constants.*;

public class AuthServiceImpl implements AuthService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String jwt;

    @Inject
    public AuthServiceImpl(JwtService jwtService) {
        jwt = jwtService.getJwtToken();
    }

    @Override
    public Jwt login(AuthCredentials login) {
        try {
            URI uri = new URI(LOGIN_ENDPOINT);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(login)))
                    .setHeader("Content-Type", "application/json")
                    .build();
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            handleErrors(response);
            final var body = response.body();
            return objectMapper.readValue(body, objectMapper.getTypeFactory().constructType(Jwt.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void register(AuthCredentials register) {
        try {
            URI uri = new URI(REGISTER_ENDPOINT);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(register)))
                    .setHeader("Content-Type", "application/json")
                    .build();
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            handleErrors(response);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void changePassword(ChangePassword changePassword) {
        try {
            URI uri = new URI(CHANGE_PASSWORD_ENDPOINT);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(changePassword)))
                    .setHeader("Content-Type", "application/json")
                    .setHeader("Authorization", "Bearer " + jwt)
                    .build();
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            handleErrors(response);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void handleErrors(HttpResponse<String> response) {
        try {
            final var body = response.body();
            final var errors = objectMapper.readValue(body, Errors.class).errors();
            if (errors.isEmpty()) {
                return;
            }
            ErrorMessageHandler.handle(errors);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
