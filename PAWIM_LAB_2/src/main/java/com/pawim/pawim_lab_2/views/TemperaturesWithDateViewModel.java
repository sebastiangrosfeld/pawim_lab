package com.pawim.pawim_lab_2.views;

import com.pawim.pawim_lab_2.models.TemperatureWithDate;

import java.util.List;

public record TemperaturesWithDateViewModel(
        List<TemperatureWithDate> temperatures
) {
}
