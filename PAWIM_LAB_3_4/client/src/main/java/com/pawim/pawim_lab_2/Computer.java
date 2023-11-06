package com.pawim.pawim_lab_2;

public record Computer(

        String name,

        String type,

        String enclosureName,

        String cpuName,

        Integer ramCapacity,

        Integer hardDiskCapacity,

        String gpuName,

        Integer powerSupply,

        Integer price
) {
}
