package com.example.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ComputerDto(
        Long id,
        @NotBlank(message = "Name cannot be blank")
        @Size(max = 255, message = "Name cannot exceed 255 characters")
        String name,
        @NotBlank(message = "Type cannot be blank")
        @Size(max = 20, message = "Type cannot exceed 20 characters")
        String type,
        @NotBlank(message = "Enclosure name cannot be blank")
        @Size(max = 255, message = "Enclosure name cannot exceed 255 characters")
        String enclosureName,
        @NotBlank(message = "CPU name cannot be blank")
        @Size(max = 255, message = "CPU name cannot exceed 255 characters")
        String cpuName,
        @Positive
        @NotNull
        Integer ramCapacity,
        @Positive
        @NotNull
        Integer hardDiskCapacity,
        @NotBlank(message = "GPU name cannot be blank")
        @Size(max = 255, message = "GPU name cannot exceed 255 characters")
        String gpuName,
        @Positive
        @NotNull
        Integer powerSupply,
        @Positive
        @NotNull
        Double price
) {
}
