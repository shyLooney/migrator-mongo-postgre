package com.pet.migrator.postgres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID houseFiasId;
    private String houseType;
    private String house;
    private String blockType;
    private String block;
    @ManyToOne
    @JoinColumn
    private Street street;
}