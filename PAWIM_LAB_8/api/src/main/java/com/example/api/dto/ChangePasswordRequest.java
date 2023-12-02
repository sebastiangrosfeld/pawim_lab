package com.example.api.dto;

public record ChangePasswordRequest(
        String username,
        String oldPassword,
        String newPassword
) {
}
