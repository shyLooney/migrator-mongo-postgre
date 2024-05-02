package com.pet.migrator.mongo.house.repository;

import com.pet.migrator.mongo.house.model.HouseDoc;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface HouseRepositoryMongo extends ReactiveCrudRepository<HouseDoc, Long> {

}
