package com.example.webclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Computer {

    private String name;
    private String type;
    private String enclosureName;
    private String cpuName;
    private Ram ram;
    private Integer hardDiskCapacity;
    private Gpu gpu;
    private Integer powerSupply;
    private Integer price;
}
