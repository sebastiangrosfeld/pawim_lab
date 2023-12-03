package com.example.desktop_app.service;

import com.example.desktop_app.model.Computer;

import java.util.List;

public interface ComputerService {

    List<Computer> getAllComputers();

    Computer getComputer(String name);

    void createComputer(Computer computer);

    void updateComputer(String name, Computer computer);

    void deleteComputer(String name);

    void deleteAllComputers();
}
