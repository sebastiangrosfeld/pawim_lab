package com.example.api.controller;

import com.example.api.dto.GpuDto;
import com.example.api.service.GpuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apiServer/gpus")
@RequiredArgsConstructor
public class GpuController {

    private final GpuService gpuService;
    private final UserDetailsService userDetailsService;

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
        String principal  = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal);
        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {
            throw new IllegalStateException("Not permission for this operation");
        }
        gpuService.deleteAllGpus();
        return ResponseEntity.noContent().build();
    }
}
