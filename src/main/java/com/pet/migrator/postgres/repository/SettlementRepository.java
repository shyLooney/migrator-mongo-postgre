package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Settlement;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface SettlementRepository extends R2dbcRepository<Settlement, Long> {
    Mono<Settlement> findBySettlementFiasId(UUID settlementFiasId);
}
