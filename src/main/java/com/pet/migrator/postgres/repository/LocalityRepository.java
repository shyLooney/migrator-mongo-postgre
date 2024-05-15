package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Locality;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface LocalityRepository extends CrudRepository<Locality, Long> {
    Locality findByLocalityFiasId(UUID localityFiasId);
    Boolean existsByLocalityFiasId(UUID localityFiasId);
}
