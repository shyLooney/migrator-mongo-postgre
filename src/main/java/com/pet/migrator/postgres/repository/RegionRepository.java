package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Region;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RegionRepository extends R2dbcRepository<Region, String> {
    Mono<Region> findByRegionFiasId(UUID regionFiasId);
}
