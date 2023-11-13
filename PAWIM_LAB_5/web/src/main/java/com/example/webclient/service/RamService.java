package com.example.webclient.service;

import com.example.webclient.model.Ram;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RamService {

    private OperationService<Ram> operationService;

    @Value("${api.baseUrl}")
    private String apiUrl;

    @Value("${api.endpoints.rams}")
    private String rams;

    @PostConstruct
    private void init() {
        operationService = new OperationService<>(apiUrl);
    }

    public List<Ram> getAllRams() {
        return operationService.getAll(rams, Ram.class);
    }

    public void createRam(Ram ram) {
        operationService.create(rams, ram);
    }

    public Ram getRam(String name) {return operationService.get(rams + "/" + name, Ram.class);}

    public void updateRam(String name, Ram ram) {
        operationService.update(rams + "/" + name, ram);
    }

    public void deleteRam(String name) {
        operationService.delete(rams + "/" + name);
    }

    public void deleteAllRams() {operationService.delete(rams + "/all");}
}
