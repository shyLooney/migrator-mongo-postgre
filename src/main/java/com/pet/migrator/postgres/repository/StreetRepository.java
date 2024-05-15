package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Street;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface StreetRepository extends CrudRepository<Street, Long> {
    Street findByStreetFiasId(UUID streetFiasId);
    Boolean existsByStreetFiasId(UUID streetFiasId);
}
