package com.pet.migrator.mongo.house.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@lombok.Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HouseDoc {
    @Id
    private ObjectId id;

    @JsonIgnore
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
