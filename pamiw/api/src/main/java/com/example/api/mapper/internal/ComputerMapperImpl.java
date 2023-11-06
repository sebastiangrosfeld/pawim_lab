package com.example.api.mapper.internal;

import com.example.api.dto.ComputerDto;
import com.example.api.mapper.ComputerMapper;
import com.example.api.model.Computer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComputerMapperImpl implements ComputerMapper {
    @Override
    public ComputerDto mapComputerToComputerDto(Computer computer) {
        return ComputerDto.builder()
                .id(computer.getId())
                .name(computer.getName())
                .type(computer.getType())
                .cpuName(computer.getCpuName())
                .enclosureName(computer.getEnclosureName())
                .ramCapacity(computer.getRamCapacity())
                .gpuName(computer.getGpuName())
                .hardDiskCapacity(computer.getHardDiskCapacity())
                .powerSupply(computer.getPowerSupply())
                .price(computer.getPrice())
                .build();
    }

    @Override
    public Computer mapComputerDtoToComputer(ComputerDto computerDto) {
        return Computer.builder()
                .id(computerDto.id())
                .name(computerDto.name())
                .type(computerDto.type())
                .cpuName(computerDto.cpuName())
                .enclosureName(computerDto.enclosureName())
                .gpuName(computerDto.gpuName())
                .hardDiskCapacity(computerDto.hardDiskCapacity())
                .ramCapacity(computerDto.ramCapacity())
                .powerSupply(computerDto.powerSupply())
                .price(computerDto.price())
                .build();
    }
}
