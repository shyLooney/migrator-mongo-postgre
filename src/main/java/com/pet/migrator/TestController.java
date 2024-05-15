package com.pet.migrator;

import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.mongo.house.repository.HouseRepositoryMongo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {
    HouseRepositoryMongo houseRepositoryMongo;

    public TestController(HouseRepositoryMongo houseRepositoryMongo) {
        this.houseRepositoryMongo = houseRepositoryMongo;
    }

    @GetMapping
    public String test() {
        houseRepositoryMongo.save(new HouseDoc());
        return "aboba";
    }

}
