package com.example.api.dataFaker;

import com.example.api.repository.ComputerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
@Transactional
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final ComputerRepository computerRepository;
    private final ComputerSeeder computerSeeder;

    @Override
    public void run(String... args) {
        if (computerRepository.findAll().isEmpty()) {
            computerSeeder.seed(20);
        }
    }

}
