package com.example.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
        @NotBlank @Size(max = 255)
        String username,
        @NotBlank @Size(max = 255)
        String oldPassword,
        @NotBlank @Size(max = 255)
        String newPassword
) {
}
