package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.House;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface HouseRepository extends R2dbcRepository<House, Long> {
    Mono<House> findByHouseFiasId(UUID houseFiasId);
}
