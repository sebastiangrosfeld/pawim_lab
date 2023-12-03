package com.example.desktop_app.service;

import com.example.desktop_app.model.Gpu;
import com.example.desktop_app.model.Ram;

import java.util.List;

public interface RamService {
    List<Ram> getAllRams();

    Ram getRam(String name);

    void createRam(Ram ram);

    void updateRam(String name, Ram ram);

    void deleteRam(String name);

    void deleteAllRams();

}
