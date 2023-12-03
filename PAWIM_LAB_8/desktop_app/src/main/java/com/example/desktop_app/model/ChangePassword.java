package com.example.desktop_app.model;

public record ChangePassword(
        String username,
        String oldPassword,
        String newPassword
) {
}
