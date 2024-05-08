package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Street;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StreetRepository extends R2dbcRepository<Street, Long> {
    Mono<Street> findByStreetFiasId(UUID streetFiasId);
}
