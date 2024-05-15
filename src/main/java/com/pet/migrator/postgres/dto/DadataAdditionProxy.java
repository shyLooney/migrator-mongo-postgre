package com.pet.migrator.postgres.dto;

import com.pet.migrator.dadata.model.DataDadata;
import com.pet.migrator.dadata.utils.DadataConnector;
import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.postgres.model.*;

import java.util.List;
import java.util.UUID;

public class DadataAdditionProxy extends HouseObjectFactory {
    private final HouseObjectFactory factory;
    private final DataDadata dadata;

    public DadataAdditionProxy(HouseDoc houseDoc) {
        super(houseDoc);
        factory = new DefaultHouseObjectFactory(houseDoc);
        dadata = getFromDadata();
    }

    private DataDadata getFromDadata() {
        DataDadata[] dadataTemp = DadataConnector.getDataByAddress(houseDoc.getAddress());

        if (dadataTemp != null) {
            return dadataTemp[0];
        }
        else
            return null;
    }

    @Override
    public Country createCountry() {
        return factory.createCountry();
    }

    @Override
    public Region createRegion(Country countryId) {
        return factory.createRegion(countryId);
    }

    @Override
    public Locality createLocality(Region regionId) {
        Locality locality = factory.createLocality(regionId);

        if (dadata != null) {
            if (dadata.getArea() == null) {
                locality.setLocalityFiasId(checkUUID(dadata.getCityFiasId()));
                locality.setLocality(dadata.getCity());
                locality.setLocalityType(dadata.getCityType());
                locality.setLocalityKladrId(dadata.getCityKladrId());
            } else {
                locality.setLocalityFiasId(checkUUID(dadata.getAreaFiasId()));
                locality.setLocality(dadata.getArea());
                locality.setLocalityType(dadata.getAreaType());
                locality.setLocalityKladrId(dadata.getAreaKladrId());
            }
        }

        return locality;
    }

    @Override
    public Settlement createSettlement(Locality localityId) {
        Settlement settlement = factory.createSettlement(localityId);

        if (dadata != null && dadata.getSettlementFiasId() != null) {
            settlement = new Settlement();
            settlement.setLocality(localityId);
            settlement.setSettlement(dadata.getSettlement());
            settlement.setSettlementType(dadata.getSettlementType());
            settlement.setSettlementFiasId(checkUUID(dadata.getSettlementFiasId()));
        }

        return settlement;
    }

    @Override
    public Street createStreet(Settlement settlementId, Locality localityId) {
        Street street = factory.createStreet(settlementId, localityId);

        if (dadata != null) {
            street.setStreet(dadata.getStreet());
            street.setStreetType(dadata.getStreetType());
            street.setStreetFiasId(checkUUID(dadata.getStreetFiasId()));
        }

        return street;
    }

    @Override
    public House createHouse(Street streetId) {
        House house = factory.createHouse(streetId);

        if (dadata != null) {
            house.setHouse(dadata.getHouse());
            house.setHouseType(dadata.getHouseType());
            house.setHouseFiasId(checkUUID(dadata.getHouseFiasId()));
            house.setBlock(dadata.getBlock());
            house.setBlockType(dadata.getBlockType());
        }

        return house;
    }

    @Override
    public Result createResult() {
        Result result = factory.createResult();

        if (dadata != null) {
            result.setFiasLevel(dadata.getFiasLevel() == null ? null : Integer.parseInt(dadata.getFiasLevel()));
        }

        return result;
    }

    @Override
    public List<Entrance> createEntrance(Result resultId) {
        return factory.createEntrance(resultId);
    }
}
