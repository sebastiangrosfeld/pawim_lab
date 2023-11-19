package com.example.api.controller;

import com.example.api.dto.ComputerDto;
import com.example.api.dto.RamDto;
import com.example.api.service.ComputerService;
import com.example.api.service.RamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/apiServer/rams")
@RequiredArgsConstructor
public class RamController {

    private final RamService ramService;

    @GetMapping
    ResponseEntity<List<RamDto>> getAllRams() {
        List<RamDto> ramDtos = ramService.getAllRams();
        return ResponseEntity.ok(ramDtos);
    }

    @GetMapping("/{name}")
    ResponseEntity<RamDto> getRam(@PathVariable(name = "name") String name) {
        RamDto ramDto = ramService.getRam(name);
        return ResponseEntity.ok(ramDto);
    }

    @PutMapping("/{name}")
    ResponseEntity<Void> updateRam(@PathVariable(name = "name") String name,@RequestBody @Valid RamDto ramDto) {
        ramService.updateRam(name, ramDto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

    @PostMapping
    ResponseEntity<Void> createRam(@RequestBody @Valid RamDto ramDto) {
        ramService.createRam(ramDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{name}")
    ResponseEntity<Void> deleteRam(@PathVariable(name = "name") String name) {
        ramService.deleteRam(name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    ResponseEntity<Void> deleteAllRams() {
        ramService.deleteAllRams();
        return ResponseEntity.noContent().build();
    }
}
