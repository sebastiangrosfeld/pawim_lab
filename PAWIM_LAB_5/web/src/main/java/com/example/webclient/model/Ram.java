package com.example.webclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ram {
    private String name;
    private Integer ramCapacity;
    private Integer memoryRate;
}
