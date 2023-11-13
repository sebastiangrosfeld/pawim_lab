package com.example.webclient.service;

import com.example.webclient.model.Computer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class OperationService<T> {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String apiUrl;

    public List<T> getAll(String url, Class<T> clazz) {
        try {
            var uri = getApiUri(url);
            var request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            var httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var body = httpResponse.body();
            return objectMapper.readValue(body, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void create(String url, T entity) {
        try {
            var uri = getApiUri(url);
            var request = HttpRequest.newBuilder()
                    .uri(uri)
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(entity)))
                    .setHeader("Content-Type", "application/json")
                    .build();
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T get(String url, Class<T> clazz) {
        try {
            var uri = getApiUri(url);
            var request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            var httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var body = httpResponse.body();
            return objectMapper.readValue(body, objectMapper.getTypeFactory().constructType(clazz));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(String url, T entity) {
        try {
            var uri = getApiUri(url);
            var request = HttpRequest.newBuilder()
                    .uri(uri)
                    .PUT(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(entity)))
                    .setHeader("Content-Type", "application/json")
                    .build();
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String url) {
        try {
            var uri = getApiUri(url);
            var request = HttpRequest.newBuilder()
                    .uri(uri)
                    .DELETE()
                    .build();
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private URI getApiUri(String url) throws URISyntaxException {
        return new URI(apiUrl + url);
    }

}
