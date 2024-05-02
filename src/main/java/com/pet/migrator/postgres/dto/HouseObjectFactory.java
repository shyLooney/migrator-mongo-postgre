package com.pet.migrator.postgres.dto;


import com.pet.migrator.mongo.house.model.HouseData;
import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.postgres.model.*;

import java.util.List;

public abstract class HouseObjectFactory {
    protected HouseDoc houseDoc;
    protected HouseData data;

    protected HouseObjectFactory(HouseDoc houseDoc) {
        this.houseDoc = houseDoc;
        data = houseDoc.getData();
    }

    public abstract Country createCountry();
    public abstract Region createRegion();
    public abstract City createCity();
    public abstract Settlement createSettlement();
    public abstract Street createStreet();
    public abstract House createHouse();
    public abstract Result createResult();
    public abstract List<Entrance> createEntrance(Long resultId);
}
