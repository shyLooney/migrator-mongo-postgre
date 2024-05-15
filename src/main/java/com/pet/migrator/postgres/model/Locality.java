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
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID localityFiasId;
    private String localityKladrId;
    private String localityType;
    private String locality;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Region region;
}
