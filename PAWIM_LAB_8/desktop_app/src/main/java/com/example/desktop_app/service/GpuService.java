package com.example.desktop_app.service;

import com.example.desktop_app.model.Computer;
import com.example.desktop_app.model.Gpu;

import java.util.List;

public interface GpuService {

    List<Gpu> getAllGpus();

    Gpu getGpu(String name);

    void createGpu(Gpu gpu);

    void updateGpu(String name, Gpu gpu);

    void deleteGpu(String name);

    void deleteAllGpus();
}
