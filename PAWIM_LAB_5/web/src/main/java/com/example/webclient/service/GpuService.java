package com.example.webclient.service;

import com.example.webclient.model.Gpu;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpuService {

    private OperationService<Gpu> operationService;

    @Value("${api.baseUrl}")
    private String apiUrl;

    @Value("${api.endpoints.gpus}")
    private String gpus;

    @PostConstruct
    private void init() {
        operationService = new OperationService<>(apiUrl);
    }

    public List<Gpu> getAllGpus() {
        return operationService.getAll(gpus, Gpu.class);
    }

    public void createGpu(Gpu gpu) {
        operationService.create(gpus, gpu);
    }

    public Gpu getGpu(String name) {return operationService.get(gpus + "/" + name, Gpu.class);}

    public void updateGpu(String name, Gpu gpu) {
        operationService.update(gpus + "/" + name, gpu);
    }

    public void deleteGpu(String name) {
        operationService.delete(gpus + "/" + name);
    }

    public void deleteAllGpus() {operationService.delete(gpus + "/all");}
}
