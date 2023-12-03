package com.example.desktop_app.model;

public record Computer(
        String name,

        String type,

        String enclosureName,

        String cpuName,

        Ram ram,

        Integer hardDiskCapacity,

        Gpu gpu,

        Integer powerSupply,

        Integer price
) {
}
