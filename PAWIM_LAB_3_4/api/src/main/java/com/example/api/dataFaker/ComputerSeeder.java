package com.example.api.dataFaker;

import com.example.api.model.Computer;
import com.example.api.repository.ComputerRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ComputerSeeder implements Seeder{

    private final ComputerRepository computerRepository;
    private final Faker faker;

    @Override
    public void seed(int objectsToSeed) {
        final Set<Computer> computers = HashSet.newHashSet(objectsToSeed);
        while (objectsToSeed > 0) {
            Computer computer = createComputer();
            if (!computers.contains(computer)) {
                objectsToSeed--;
                computerRepository.save(computer);
                computers.add(computer);
            }
        }
    }

    private Computer createComputer() {
        return Computer.builder()
                .name(faker.pokemon().name())
                .type(faker.food().fruit())
                .hardDiskCapacity(faker.number().numberBetween(100,3000))
                .enclosureName(faker.food().vegetable())
                .cpuName(faker.superhero().name())
                .gpuName(faker.leagueOfLegends().champion())
                .powerSupply(faker.number().numberBetween(100,900))
                .ramCapacity(faker.number().numberBetween(2,128))
                .price(faker.number().numberBetween(2000,5000))
                .build();
    }
}
