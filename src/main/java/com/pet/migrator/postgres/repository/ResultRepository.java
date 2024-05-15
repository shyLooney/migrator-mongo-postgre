package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Result;
import org.springframework.data.repository.CrudRepository;

public interface ResultRepository extends CrudRepository<Result, Long> {
    Result findByAddress(String address);
    Boolean existsByAddress(String address);
}
