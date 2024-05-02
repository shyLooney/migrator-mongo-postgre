package com.pet.migrator.postgres.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    private Long id;
    private UUID cityFiasId;
    private String cityKladrId;
    private String cityType;
    private String city;
}
