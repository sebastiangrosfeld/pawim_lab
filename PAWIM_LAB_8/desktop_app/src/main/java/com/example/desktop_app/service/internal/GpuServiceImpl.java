package com.example.desktop_app.service.internal;

import com.example.desktop_app.ErrorMessageHandler;
import com.example.desktop_app.model.Computer;
import com.example.desktop_app.model.Errors;
import com.example.desktop_app.model.Gpu;
import com.example.desktop_app.service.GpuService;
import com.example.desktop_app.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.example.desktop_app.Constants.GPU_ENDPOINT;

public class GpuServiceImpl implements GpuService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String jwt;

    @Inject
    public GpuServiceImpl(JwtService jwtService) {
        jwt = jwtService.getJwtToken();
    }

    @Override
    public List<Gpu> getAllGpus() {
        try {
            URI uri = new URI(GPU_ENDPOINT);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .setHeader("Authorization", "Bearer " + jwt)
                    .build();
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            final var body = response.body();
            System.out.println("ok");
            List<Gpu> result = objectMapper.readValue(body, objectMapper.getTypeFactory().constructCollectionType(List.class, Gpu.class));
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Gpu getGpu(String name) {
        try {
            String url = GPU_ENDPOINT + "/" + name;
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
            return objectMapper.readValue(body, objectMapper.getTypeFactory().constructType(Gpu.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createGpu(Gpu gpu) {
        try {
            String url = GPU_ENDPOINT;
            URI uri = new URI(url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(gpu)))
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
    public void updateGpu(String name, Gpu gpu) {

    }

    @Override
    public void deleteGpu(String name) {
        try {
            URI uri = new URI(GPU_ENDPOINT + "/" + name);
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
    public void deleteAllGpus() {
        try {
            URI uri = new URI(GPU_ENDPOINT + "/all");
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
