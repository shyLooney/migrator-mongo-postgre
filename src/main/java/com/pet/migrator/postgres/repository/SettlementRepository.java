package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Settlement;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SettlementRepository extends CrudRepository<Settlement, Long> {
    Settlement findBySettlementFiasId(UUID settlementFiasId);
    Boolean existsBySettlementFiasId(UUID settlementFiasId);
}
