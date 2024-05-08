package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Locality;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface LocalityRepository extends R2dbcRepository<Locality, Long> {
    Mono<Locality> findByLocalityFiasId(UUID localityFiasId);
}
