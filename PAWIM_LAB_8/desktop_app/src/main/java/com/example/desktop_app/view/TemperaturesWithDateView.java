package com.example.desktop_app.view;

import com.example.desktop_app.model.TemperatureWithDate;

import java.util.List;

public record TemperaturesWithDateView(
        List<TemperatureWithDate> temperatures
) {
}
