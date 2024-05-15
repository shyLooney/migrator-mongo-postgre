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
public class Settlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID settlementFiasId;
    private String settlementType;
    private String settlement;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Locality locality;
}