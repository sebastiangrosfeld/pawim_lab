package com.example.webclient.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComputerForm {

    String name;
    String type;
    String enclosureName;
    String cpuName;
    String ramName;
    Integer hardDiskCapacity;
    String gpuName;
    Integer powerSupply;
    Integer price;
}
