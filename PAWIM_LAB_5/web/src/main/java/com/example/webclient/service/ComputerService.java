package com.example.webclient.service;

import com.example.webclient.model.Computer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {

    private OperationService<Computer> operationService;

    @Value("${api.baseUrl}")
    private String apiUrl;

    @Value("${api.endpoints.computers}")
    private String computers;

    @PostConstruct
    private void init() {
        operationService = new OperationService<>(apiUrl);
    }

    public List<Computer> getAllComputers() {
        return operationService.getAll(computers, Computer.class);
    }

    public void createComputer(Computer computer) {
        operationService.create(computers, computer);
    }

    public Computer getComputer(String name) {return operationService.get(computers + "/" + name, Computer.class);}

    public void updateComputer(String name, Computer computer) {
        operationService.update(computers + "/" + name, computer);
    }

    public void deleteComputer(String name) {
        operationService.delete(computers + "/" + name);
    }

    public void deleteAllComputers() {operationService.delete(computers + "/all");}
}
