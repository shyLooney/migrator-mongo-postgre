package com.pet.migrator;

import com.pet.migrator.mongo.house.model.Entrance;
import com.pet.migrator.mongo.house.model.HouseData;
import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.mongo.house.model.Structure;
import com.pet.migrator.postgres.dto.DefaultHouseObjectFactory;
import com.pet.migrator.postgres.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class DefaultHouseObjectFactoryTest implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.empty();
    }

    public static List<HouseDoc> initObject() {
        return TestDataLoader.load("src/main/resources/test-mongo");
    }

    @ParameterizedTest
    @MethodSource("initObject")
    void testInitialize(HouseDoc houseDoc) {
        DefaultHouseObjectFactory factory = new DefaultHouseObjectFactory(houseDoc);
        Country country = factory.createCountry();
        Region region = factory.createRegion(country);
        Locality locality = factory.createLocality(region);
        Settlement settlement = factory.createSettlement(locality);
        Street street = factory.createStreet(settlement, locality);
        House house = factory.createHouse(street);
        Result result = factory.createResult();
        List<com.pet.migrator.postgres.model.Entrance> entrances = factory.createEntrance(result);


        System.out.println(settlement);
        testInitializeCountry(country, houseDoc.getData());
        testInitializeRegion(region, houseDoc.getData());
        testInitializeCity(locality, houseDoc.getData());
        testInitializeSettlement(settlement, houseDoc.getData());
        testInitializeStreet(street, houseDoc.getData());
        testInitializeHouse(house, houseDoc.getData());
        testInitializeResult(result, houseDoc);
        testInitializeEntrances(entrances, houseDoc);
    }

    void testInitializeCountry(Country country, HouseData houseData) {
        Assertions.assertAll("Test initialize countries fields",
                () -> Assertions.assertEquals(country.getCountry(), houseData.getCountry()),
                () -> Assertions.assertEquals(country.getCountryIsoCode(), houseData.getCountryIsoCode())
        );
    }

    void testInitializeRegion(Region region, HouseData houseData) {
        Assertions.assertAll("Test initialize regions fields",
                () -> Assertions.assertEquals(region.getRegion(), houseData.getRegion()),
                () -> Assertions.assertEquals(convertUUID(region.getRegionFiasId()), houseData.getRegionFiasId())
        );
    }

    void testInitializeCity(Locality locality, HouseData houseData) {
        Assertions.assertAll("Test initialize cities fields",
                () -> Assertions.assertEquals(locality.getLocality(), houseData.getCity()),
                () -> Assertions.assertEquals(locality.getLocalityType(), houseData.getCityType()),
                () -> Assertions.assertEquals(convertUUID(locality.getLocalityFiasId()), houseData.getCityFiasId()),
                () -> Assertions.assertEquals(locality.getLocalityKladrId(), houseData.getCityKladrId())
        );
    }

    void testInitializeSettlement(Settlement settlement, HouseData houseData) {
        if (settlement == null) {
            Assertions.assertNull(houseData.getSettlement());
            Assertions.assertNull(houseData.getSettlementType());
            Assertions.assertNull(houseData.getSettlementFiasId());
        } else {
            Assertions.assertAll("Test initialize settlements fields",
                    () -> Assertions.assertEquals(settlement.getSettlement(), houseData.getSettlement()),
                    () -> Assertions.assertEquals(convertUUID(settlement.getSettlementFiasId()), houseData.getSettlementFiasId()),
                    () -> Assertions.assertEquals(settlement.getSettlementType(), houseData.getSettlementType())
            );
        }
    }

    void testInitializeStreet(Street street, HouseData houseData) {
        Assertions.assertAll("Test initialize streets fields",
                () -> Assertions.assertEquals(street.getStreet(), houseData.getStreet()),
                () -> Assertions.assertEquals(convertUUID(street.getStreetFiasId()), houseData.getStreetFiasId()),
                () -> Assertions.assertEquals(street.getStreetType(), houseData.getStreetType())
        );
    }

    void testInitializeHouse(House house, HouseData houseData) {
        Assertions.assertAll("Test initialize houses fields",
                () -> Assertions.assertEquals(house.getHouse(), houseData.getHouse()),
                () -> Assertions.assertEquals(convertUUID(house.getHouseFiasId()), isUUID(houseData.getHouseFiasId())),
                () -> Assertions.assertEquals(house.getHouseType(), houseData.getHouseType()),
                () -> Assertions.assertEquals(house.getBlock(), houseData.getBlock()),
                () -> Assertions.assertEquals(house.getBlockType(), houseData.getBlockType())
        );
    }

    void testInitializeResult(Result result, HouseDoc houseDoc) {
        Assertions.assertAll("Test initialize results fields",
                () -> Assertions.assertEquals(result.getAddress(), houseDoc.getAddress()));
    }

    void testInitializeEntrances(List<com.pet.migrator.postgres.model.Entrance> entrance, HouseDoc houseDoc) {
        if (houseDoc.getStructure() == null || houseDoc.getStructure().getEntrances() == null) {
            Assertions.assertEquals(0, entrance.size());
        } else {
            Assertions.assertEquals(entrance.size(), houseDoc.getStructure().getEntrances().size());
            for (int i = 0; i < entrance.size(); i++) {
                Assertions.assertEquals(entrance.get(i).getNumber(), houseDoc.getStructure().getEntrances().get(i).getNumber());
                Assertions.assertEquals(entrance.get(i).getFromEn(), houseDoc.getStructure().getEntrances().get(i).getFrom());
                Assertions.assertEquals(entrance.get(i).getToEn(), houseDoc.getStructure().getEntrances().get(i).getTo());
            }
        }
    }

    private String convertUUID(UUID uuid) {
        return uuid == null ? null : uuid.toString();
    }

    private String isUUID(String uuidString) {
        if (uuidString != null && uuidString.length() != 36)
            return null;
        return uuidString;
    }

}