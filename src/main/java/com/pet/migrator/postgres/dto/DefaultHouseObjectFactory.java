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
    public Region createRegion(Long countryId) {

        return new Region(
                null,
                UUID.fromString(data.getRegionFiasId()).toString().equals(data.getRegionFiasId()) ? UUID.fromString(data.getRegionFiasId()) : null,
                data.getRegion(),
                data.getTimeZone(),
                countryId
        );
    }

    @Override
    public Locality createLocality(Long regionId) {
        if (data.getCityFiasId() == null)
            return null;

        return new Locality(
                null,
                UUID.fromString(data.getCityFiasId()).toString().equals(data.getCityFiasId()) ? UUID.fromString(data.getCityFiasId()) : null,
                data.getCityKladrId(),
                data.getCityType(),
                data.getCity(),
                regionId
        );
    }

    @Override
    public Settlement createSettlement(Long localityId) {
        if (data.getSettlementFiasId() == null)
            return null;

        return new Settlement(
                null,
                UUID.fromString(data.getSettlementFiasId()).toString().equals(data.getSettlementFiasId()) ? UUID.fromString(data.getSettlementFiasId()) : null,
                data.getSettlementType(),
                data.getSettlement(),
                localityId
        );
    }

    @Override
    public Street createStreet(Long settlementId, Long localityId) {
        if (data.getStreetFiasId() == null)
            return null;

        return new Street(
                null,
                UUID.fromString(data.getStreetFiasId()).toString().equals(data.getStreetFiasId()) ? UUID.fromString(data.getStreetFiasId()) : null,
                data.getStreetType(),
                data.getStreet(),
                settlementId,
                localityId
        );
    }

    @Override
    public House createHouse(Long streetId) {
        UUID uuid;

        if (data.getHouse() == null) {
            return null;
        }

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
                null,
                houseDoc.getAddress()
        );
    }

    @Override
    public List<Entrance> createEntrance(Long resultId) {
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
