package com.example.api.service;

import com.example.api.dto.AuthCredentialsDto;
import com.example.api.dto.ChangePasswordRequest;
import com.example.api.dto.TokenDto;

public interface AuthService {

    TokenDto login(AuthCredentialsDto authCredentialsDto);

    void register(AuthCredentialsDto authCredentialsDto);

    void changePassword(ChangePasswordRequest changePasswordRequest);
}
