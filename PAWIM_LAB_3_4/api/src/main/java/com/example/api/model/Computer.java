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
    @Getter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private String type;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private String enclosureName;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private String cpuName;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private Integer ramCapacity;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private Integer hardDiskCapacity;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private String gpuName;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private Integer powerSupply;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private Integer price;

}
