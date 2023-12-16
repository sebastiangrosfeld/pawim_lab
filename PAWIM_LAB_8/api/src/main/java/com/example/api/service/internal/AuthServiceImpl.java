package com.example.api.service.internal;

import com.example.api.configuration.JwtConfig;
import com.example.api.dto.AuthCredentialsDto;
import com.example.api.dto.ChangePasswordRequest;
import com.example.api.dto.TokenDto;
import com.example.api.model.AppUser;
import com.example.api.model.Roles;
import com.example.api.repository.UserRepository;
import com.example.api.service.AuthService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;

    @Override
    public TokenDto login(AuthCredentialsDto authCredentialsDto) {
        AppUser user = userRepository.findByUsername(authCredentialsDto.username())
                .orElseThrow(() -> new IllegalStateException("User not exist"));
        checkPassword(authCredentialsDto.password(), user.getPassword());
        String jwt = Jwts.builder()
                .subject(user.getUsername())
                .claim("authorities", user.getAuthorities())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationTime()))
                .signWith(jwtConfig.getSecretKey())
                .compact();
        return new TokenDto(jwt);
    }

    @Override
    public void register(AuthCredentialsDto authCredentialsDto) {
        if(userRepository.existsByUsername(authCredentialsDto.username())) {
            throw new IllegalStateException("User with that username already exist");
        }
        String encPassword = passwordEncoder.encode(authCredentialsDto.password());
        AppUser user = AppUser.builder()
                .username(authCredentialsDto.username())
                .password(encPassword)
                .role(Roles.USER)
                .build();
        userRepository.save(user);
    }

    @Override
    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        AppUser user = userRepository.findByUsername(changePasswordRequest.username())
                .orElseThrow(() -> new IllegalStateException("User not exist"));
        checkPassword(changePasswordRequest.oldPassword(), user.getPassword());
        String encNewPassword = passwordEncoder.encode(changePasswordRequest.newPassword());
        user.setPassword(encNewPassword);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private void checkPassword(String password, String encPassword) {
        if(passwordEncoder.matches(password, encPassword)) {
            return;
        } else {
            throw new IllegalStateException("Incorrect password");
        }
    }
}
