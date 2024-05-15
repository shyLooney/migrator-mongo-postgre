package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Region;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;


public interface RegionRepository extends CrudRepository<Region, String> {
    Region findByRegionFiasId(UUID regionFiasId);
    Boolean existsByRegionFiasId(UUID regionFiasId);
}
