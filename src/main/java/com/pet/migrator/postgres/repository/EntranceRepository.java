package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Entrance;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface EntranceRepository extends R2dbcRepository<Entrance, Long> {
}
