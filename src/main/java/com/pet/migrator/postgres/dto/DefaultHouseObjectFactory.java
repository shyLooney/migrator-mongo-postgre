package com.pet.migrator.postgres.dto;

import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.postgres.model.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class DefaultHouseObjectFactory extends HouseObjectFactory {

    public DefaultHouseObjectFactory(HouseDoc houseDoc) {
        super(houseDoc);
    }

    @Override
    public Country createCountry() {
        return new Country(
                null,
                data.getCountry(),
                data.getCountryIsoCode()
        );
    }

    @Override
    public Region createRegion(Country countryId) {

        return new Region(
                null,
                checkUUID(data.getRegionFiasId()),
                data.getRegion(),
                data.getTimeZone(),
                countryId
        );
    }

    @Override
    public Locality createLocality(Region regionId) {
//        if (data.getCityFiasId() == null) {
//            Locality locality = new Locality();
//            locality.setRegion(regionId);
//            return locality;
//        }

        return new Locality(
                null,
                checkUUID(data.getCityFiasId()),
                data.getCityKladrId(),
                data.getCityType(),
                data.getCity(),
                regionId
        );
    }

    @Override
    public Settlement createSettlement(Locality localityId) {
        if (data.getSettlementFiasId() == null) {
            return null;
        }

        return new Settlement(
                null,
                checkUUID(data.getSettlementFiasId()),
                data.getSettlementType(),
                data.getSettlement(),
                localityId
        );
    }

    @Override
    public Street createStreet(Settlement settlementId, Locality localityId) {
//        if (data.getStreetFiasId() == null) {
//            Street street = new Street();
//            street.setSettlement(settlementId);
//            street.setLocality(localityId);
//            return street;
//        }

        return new Street(
                null,
                checkUUID(data.getStreetFiasId()),
                data.getStreetType(),
                data.getStreet(),
                settlementId,
                localityId
        );
    }

    @Override
    public House createHouse(Street streetId) {
        UUID uuid;

//        if (data.getHouse() == null) {
//            House house = new House();
//            house.setStreet(streetId);
//            return house;
//        }

        if (data.getHouseFiasId() == null || data.getHouseFiasId().length() != 36)
            uuid = null;
        else
            uuid = UUID.fromString(data.getHouseFiasId()).toString().equals(data.getHouseFiasId()) ? UUID.fromString(data.getHouseFiasId()) : null;


        return new House(
                null,
                uuid,
                data.getHouseType(),
                data.getHouse(),
                data.getBlockType(),
                data.getBlock(),
                streetId
        );
    }

    @Override
    public Result createResult() {
        return new Result(
                null,
                data.getFiasLevel() == null ? null : Integer.parseInt(data.getFiasLevel()),
                null,
                houseDoc.getAddress()
        );
    }

    @Override
    public List<Entrance> createEntrance(Result resultId) {
        List<Entrance> entrances = new ArrayList<>();

        if (houseDoc.getStructure() == null || houseDoc.getStructure().getEntrances() == null) {
            return entrances;
        }

        for (var item : houseDoc.getStructure().getEntrances()) {
            entrances.add(new Entrance(
                    null,
                    item.getNumber(),
                    item.getFrom(),
                    item.getTo(),
                    resultId
            ));
        }
        return entrances;
    }
}
