package com.pawim.pawim_lab_2.models;

public record Temperature(
        String temperature,
        String metric
) {
    @Override
    public String toString() {
        return temperature + " " + metric;
    }
}
