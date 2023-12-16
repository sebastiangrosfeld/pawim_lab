package com.example.api.dataFaker;

import com.example.api.model.AppUser;
import com.example.api.model.Roles;
import com.example.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSeeder implements Seeder{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void seed(int objectsToSeed) {
        String encodedPassword = passwordEncoder.encode("admin");
        AppUser user = AppUser.builder()
                .username("admin")
                .password(encodedPassword)
                .role(Roles.ADMIN)
                .build();
        userRepository.save(user);
    }
}
