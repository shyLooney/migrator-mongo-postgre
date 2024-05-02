package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Region;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface RegionRepository extends R2dbcRepository<Region, String> {
}
