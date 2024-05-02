package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Result;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ResultRepository extends R2dbcRepository<Result, Long> {
}
