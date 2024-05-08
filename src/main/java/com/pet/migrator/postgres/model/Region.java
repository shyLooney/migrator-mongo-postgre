package com.pet.migrator.postgres.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    @Id
    private Long id;
    private UUID regionFiasId;
    private String region;
    private String timeZone;
    private Long countryId;
}
