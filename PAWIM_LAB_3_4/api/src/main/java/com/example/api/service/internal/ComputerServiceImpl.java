package com.example.api.service.internal;

import com.example.api.dto.ComputerDto;
import com.example.api.mapper.ComputerMapper;
import com.example.api.model.Computer;
import com.example.api.repository.ComputerRepository;
import com.example.api.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ComputerServiceImpl implements ComputerService {

    private final ComputerRepository computerRepository;
    private final ComputerMapper computerMapper;
    @Override
    public List<ComputerDto> getAllComputers() {
        return computerRepository.findAll()
                .stream()
                .map(computerMapper::mapComputerToComputerDto)
                .toList();
    }

    @Override
    public ComputerDto getComputer(String name) {
        return computerMapper.mapComputerToComputerDto(findComputerByName(name));
    }

    @Override
    public void updateComputer(String name, ComputerDto computerDto) {
        if (name.isEmpty())
            throw new IllegalArgumentException("name cannot be bull");
        Computer computer = findComputerByName(name);
        updateComputer(computer, computerDto);
    }

    @Override
    public void deleteComputer(String name) {
        Computer computer = findComputerByName(name);
        computerRepository.delete(computer);
    }

    @Override
    public void createComputer(ComputerDto computerDto) {
        Computer computer = computerMapper.mapComputerDtoToComputer(computerDto);
        if(computerRepository.existsByName(computer.getName()))
            throw new IllegalStateException("Created computer already exist");
        computerRepository.save(computer);
    }

    @Override
    public void deleteAllComputers() {
        if (computerRepository.findAll().isEmpty())
            throw new IllegalStateException("Database is empty");
        computerRepository.deleteAll();
    }

    private Computer findComputerByName(String name) {
        return computerRepository.findComputerByName(name)
                .orElseThrow(() -> new IllegalStateException("Searched computer not exist"));
    }

    private void updateComputer(Computer computer, ComputerDto computerDto) {
        computer.setName(computerDto.name());
        computer.setType(computerDto.type());
        computer.setCpuName(computerDto.cpuName());
        computer.setEnclosureName(computerDto.enclosureName());
        computer.setGpuName(computerDto.gpuName());
        computer.setRamCapacity(computerDto.ramCapacity());
        computer.setHardDiskCapacity(computerDto.hardDiskCapacity());
        computer.setPowerSupply(computerDto.powerSupply());
        computer.setPrice(computerDto.price());
    }
}
