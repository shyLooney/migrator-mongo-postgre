package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.House;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;



public interface HouseRepository extends CrudRepository<House, Long> {
    House findByHouseFiasId(UUID houseFiasId);
    Boolean existsByHouseFiasId(UUID houseFiasId);
}
