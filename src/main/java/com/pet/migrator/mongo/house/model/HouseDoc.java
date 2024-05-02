package com.pet.migrator.mongo.house.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.relational.core.mapping.Column;

@lombok.Data
@Document
public class HouseDoc {
    @Id
    private ObjectId id;

    private ObjectId companyId;

    private Integer numberOfPorches;
    /**
     * supplied by fias
     */
    private HouseData data;

    /**
     * composed based on fias location data
     */
    private String address;

    private Structure structure;
}
