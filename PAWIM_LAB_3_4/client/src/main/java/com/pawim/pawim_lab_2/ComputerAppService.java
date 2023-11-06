package com.pawim.pawim_lab_2;

import java.util.List;

public interface ComputerAppService {

    List<Computer> getAllComputers();

    Computer getComputer(String name);

    void createComputer(Computer computer);

    void updateComputer(String name, Computer computer);

    void deleteComputer(String name);

    void deleteAllComputers();
}
