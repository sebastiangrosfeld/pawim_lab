package com.example.webclient.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RamForm {

    private String name;
    private Integer ramCapacity;
    private Integer memoryRate;
}
