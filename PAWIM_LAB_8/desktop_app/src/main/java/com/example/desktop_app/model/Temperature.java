package com.example.desktop_app.model;

public record Temperature(
        String temperature,
        String metric
) {
    @Override
    public String toString() {
        return temperature + " " + metric;
    }
}
