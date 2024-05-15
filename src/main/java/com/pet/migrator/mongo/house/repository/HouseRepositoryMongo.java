package com.pet.migrator.mongo.house.repository;

import com.pet.migrator.mongo.house.model.HouseDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HouseRepositoryMongo extends MongoRepository<HouseDoc, Long> {

}
