package com.example.desktop_app.service.internal;

import com.example.desktop_app.ErrorMessageHandler;
import com.example.desktop_app.model.Computer;
import com.example.desktop_app.model.Errors;
import com.example.desktop_app.service.AuthService;
import com.example.desktop_app.service.ComputerService;
import com.example.desktop_app.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.example.desktop_app.Constants.COMPUTERS_ENDPOINT;

public class ComputerServiceImpl implements ComputerService {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String jwt;

    @Inject
    public ComputerServiceImpl(JwtService jwtService) {
        jwt = jwtService.getJwtToken();
    }

    @Override
    public List<Computer> getAllComputers() {
        try {
            URI uri = new URI(COMPUTERS_ENDPOINT);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .setHeader("Authorization", "Bearer " + jwt)
                    .build();
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            final var body = response.body();
            System.out.println("ok");
            List<Computer> result = objectMapper.readValue(body, objectMapper.getTypeFactory().constructCollectionType(List.class, Computer.class));
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Computer getComputer(String name) {
        try {
            String url = COMPUTERS_ENDPOINT + "/" + name;
            URI uri = new URI(url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .setHeader("Authorization", "Bearer " + jwt)
                    .build();
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//            handleErrors(response);
            final var body = response.body();
            System.out.println("ok");
            return objectMapper.readValue(body, objectMapper.getTypeFactory().constructType(Computer.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createComputer(Computer computer) {
        try {
            String url = COMPUTERS_ENDPOINT;
            URI uri = new URI(url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(computer)))
                    .setHeader("Authorization", "Bearer " + jwt)
                    .setHeader("Content-Type", "application/json")
                    .build();
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            handleErrors(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateComputer(String name, Computer computer) {
        try {
            String url = COMPUTERS_ENDPOINT + "/" + name;
            URI uri = new URI(url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(computer)))
                    .setHeader("Authorization", "Bearer " + jwt)
                    .setHeader("Content-Type", "application/json")
                    .build();
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            handleErrors(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteComputer(String name) {
        try {
            URI uri = new URI(COMPUTERS_ENDPOINT + "/" + name);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .DELETE()
                    .setHeader("Authorization", "Bearer " + jwt)
                    .build();
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            handleErrors(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllComputers() {
        try {
            URI uri = new URI(COMPUTERS_ENDPOINT + "/all");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .DELETE()
                    .setHeader("Authorization", "Bearer " + jwt)
                    .build();
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            handleErrors(response);
        } catch (Exception e) {
            e.printStackTrace();
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
