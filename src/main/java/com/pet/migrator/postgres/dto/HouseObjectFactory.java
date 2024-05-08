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
    public abstract Region createRegion(Long countryId);
    public abstract Locality createLocality(Long regionId);
    public abstract Settlement createSettlement(Long localityId);
    public abstract Street createStreet(Long settlementId, Long localityId);
    public abstract House createHouse(Long streetId);
    public abstract Result createResult();
    public abstract List<Entrance> createEntrance(Long resultId);
}
