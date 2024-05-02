package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Street;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface StreetRepository extends R2dbcRepository<Street, Long> {
}
