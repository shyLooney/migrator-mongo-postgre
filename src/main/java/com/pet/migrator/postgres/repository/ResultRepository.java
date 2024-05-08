package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Result;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ResultRepository extends R2dbcRepository<Result, Long> {
    Mono<Result> findByAddress(String address);
}
