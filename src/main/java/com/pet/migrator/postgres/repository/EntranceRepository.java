package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Entrance;
import org.springframework.data.repository.CrudRepository;

public interface EntranceRepository extends CrudRepository<Entrance, Long> {
}
