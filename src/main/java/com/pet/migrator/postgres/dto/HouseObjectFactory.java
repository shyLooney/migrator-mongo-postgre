package com.pet.migrator.postgres.dto;


import com.pet.migrator.mongo.house.model.HouseData;
import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.postgres.model.*;

import java.util.List;
import java.util.UUID;

public abstract class HouseObjectFactory {
    protected HouseDoc houseDoc;
    protected HouseData data;

    protected HouseObjectFactory(HouseDoc houseDoc) {
        this.houseDoc = houseDoc;
        data = houseDoc.getData();
    }


    public abstract Country createCountry();
    public abstract Region createRegion(Country countryId);
    public abstract Locality createLocality(Region regionId);
    public abstract Settlement createSettlement(Locality localityId);
    public abstract Street createStreet(Settlement settlementId, Locality localityId);
    public abstract House createHouse(Street streetId);
    public abstract Result createResult();
    public abstract List<Entrance> createEntrance(Result resultId);

    protected UUID checkUUID(String uuid) {
        if (uuid == null)
            return null;
        return UUID.fromString(uuid).toString().equals(uuid) ? UUID.fromString(uuid) : null;
    }
}
