package com.pet.migrator.postgres.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Locality {
    @Id
    private Long id;
    private UUID localityFiasId;
    private String localityKladrId;
    private String localityType;
    private String locality;
    private Long regionId;
}
