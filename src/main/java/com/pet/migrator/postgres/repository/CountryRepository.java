package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Country;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CountryRepository extends R2dbcRepository<Country, Long> {
}
