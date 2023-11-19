package com.example.api.controller;

import com.example.api.dto.GpuDto;
import com.example.api.service.GpuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apiServer/gpus")
@RequiredArgsConstructor
public class GpuController {

    private final GpuService gpuService;

    @GetMapping
    ResponseEntity<List<GpuDto>> getAllRams() {
        List<GpuDto> gpuDtos = gpuService.getAllGpus();
        return ResponseEntity.ok(gpuDtos);
    }

    @GetMapping("/{name}")
    ResponseEntity<GpuDto> getRam(@PathVariable(name = "name") String name) {
        GpuDto gpuDto = gpuService.getGpu(name);
        return ResponseEntity.ok(gpuDto);
    }

    @PutMapping("/{name}")
    ResponseEntity<Void> updateRam(@PathVariable(name = "name") String name,@RequestBody @Valid GpuDto gpuDto) {
        gpuService.updateGpu(name, gpuDto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

    @PostMapping
    ResponseEntity<Void> createRam(@RequestBody @Valid GpuDto gpuDto) {
        gpuService.createGpu(gpuDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{name}")
    ResponseEntity<Void> deleteRam(@PathVariable(name = "name") String name) {
        gpuService.deleteGpu(name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    ResponseEntity<Void> deleteAllRams() {
        gpuService.deleteAllGpus();
        return ResponseEntity.noContent().build();
    }
}
