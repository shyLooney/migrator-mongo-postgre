package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.City;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CityRepository extends R2dbcRepository<City, Long> {
}
