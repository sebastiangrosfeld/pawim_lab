package com.example.api.controller;

import com.example.api.dto.AuthCredentialsDto;
import com.example.api.dto.ChangePasswordRequest;
import com.example.api.dto.TokenDto;
import com.example.api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apiServer/authorization")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody @Valid AuthCredentialsDto authCredentialsDto) {
        TokenDto jwt = authService.login(authCredentialsDto);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid AuthCredentialsDto authCredentialsDto) {
        authService.register(authCredentialsDto);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest) {
        authService.changePassword(changePasswordRequest);
        return ResponseEntity.noContent().build();
    }
}
