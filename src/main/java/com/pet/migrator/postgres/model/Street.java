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
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID streetFiasId;
    private String streetType;
    private String street;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Settlement settlement;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Locality locality;
}