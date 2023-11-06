package com.example.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String enclosureName;

    @Column(nullable = false)
    private String cpuName;

    @Column(nullable = false)
    private Integer ramCapacity;

    @Column(nullable = false)
    private Integer hardDiskCapacity;

    @Column(nullable = false)
    private String gpuName;

    @Column(nullable = false)
    private Integer powerSupply;

    @Column(nullable = false)
    private Double price;

}
