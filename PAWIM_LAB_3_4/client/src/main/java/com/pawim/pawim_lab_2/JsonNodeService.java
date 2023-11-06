package com.pawim.pawim_lab_2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JsonNodeService {

    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode getJsonResponseBody(String url) {
        try {
            var locationRequest = HttpRequest.newBuilder().GET().uri(new URI(url)).build();
            var response = httpClient.send(locationRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readTree(response.body());
        } catch (Exception e) {
            return null;
        }
    }
}
