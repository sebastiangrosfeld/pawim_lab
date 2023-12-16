package com.example.api.controller;

import com.example.api.dto.ComputerDto;
import com.example.api.service.ComputerService;
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
@RequestMapping("/apiServer/computers")
@RequiredArgsConstructor
public class ComputerController {

    private final ComputerService computerService;
    private final UserDetailsService userDetailsService;

    @GetMapping
    ResponseEntity<List<ComputerDto>> getAllComputers() {
        List<ComputerDto> computerDtos = computerService.getAllComputers();
        return ResponseEntity.ok(computerDtos);
    }

    @GetMapping("/{name}")
    ResponseEntity<ComputerDto> getComputer(@PathVariable(name = "name") String name) {
        ComputerDto computerDto = computerService.getComputer(name);
        return ResponseEntity.ok(computerDto);
    }

    @PutMapping ("/{name}")
    ResponseEntity<?> updateComputer(@PathVariable(name = "name") String name,@RequestBody @Valid ComputerDto computerDto) {
        computerService.updateComputer(name, computerDto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

    @PostMapping
    ResponseEntity<Void> createComputer(@RequestBody @Valid ComputerDto computerDto) {
        computerService.createComputer(computerDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{name}")
    ResponseEntity<Void> deleteComputer(@PathVariable(name = "name") String name) {
        computerService.deleteComputer(name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/all")
    ResponseEntity<Void> deleteAllComputers() {
        String principal  = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal);
        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("USER"))) {
            throw new IllegalStateException("Not permission for this operation");
        }
        computerService.deleteAllComputers();
        return ResponseEntity.noContent().build();
    }
}
