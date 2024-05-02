package com.pet.migrator.postgres.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    private Long id;
    private String country;
    private String countryIsoCode;
}
