package com.pet.migrator;

import com.pet.migrator.mongo.house.model.HouseData;
import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.postgres.dto.DefaultHouseObjectFactory;
import com.pet.migrator.postgres.dto.HouseDTO;
import com.pet.migrator.postgres.model.*;
import com.pet.migrator.postgres.repository.*;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class HouseDTO_SaveTest {
    private HouseDTO houseDTO;
    private CountryRepository countryRepository;
    private RegionRepository regionRepository;
    private LocalityRepository localityRepository;
    private SettlementRepository settlementRepository;
    private StreetRepository streetRepository;
    private HouseRepository houseRepository;
    private ResultRepository resultRepository;
    private EntranceRepository entranceRepository;

    public static List<HouseDoc> initObject() {
        return TestDataLoader.load("src/main/resources/test-mongo");
    }

    @ParameterizedTest
    @MethodSource("initObject")
    void initializeEntity(HouseDoc houseDoc) {
//        houseDTO.save(houseDoc);
//        Country country = countryRepository.findByCountryIsoCode(houseDoc.getData().getCountryIsoCode());
//        Region region = factory.createRegion(country);
//        Locality locality = factory.createLocality(region);
//        Settlement settlement = factory.createSettlement(locality);
//        Street street = factory.createStreet(settlement, locality);
//        House house = factory.createHouse(street);
//        Result result = factory.createResult();
//        List<com.pet.migrator.postgres.model.Entrance> entrances = factory.createEntrance(result);


    }

    void saveEntrancesTest(List<com.pet.migrator.postgres.model.Entrance> entrance, HouseDoc houseDoc) {
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

    void saveResultTest(HouseDoc houseDoc) {
        Result result = resultRepository.findByAddress(houseDoc.getAddress());

        Assertions.assertAll("Test save results entity",
                () -> Assertions.assertEquals(result.getAddress(), houseDoc.getAddress()));
    }

    void saveHouseTest(HouseData houseData) {
        House house = houseRepository.findByHouseFiasId(checkUUID(houseData.getHouseFiasId()));

        Assertions.assertAll("Test save houses entity",
                () -> Assertions.assertEquals(house.getHouse(), houseData.getHouse()),
                () -> Assertions.assertEquals(convertUUID(house.getHouseFiasId()), isUUID(houseData.getHouseFiasId())),
                () -> Assertions.assertEquals(house.getHouseType(), houseData.getHouseType()),
                () -> Assertions.assertEquals(house.getBlock(), houseData.getBlock()),
                () -> Assertions.assertEquals(house.getBlockType(), houseData.getBlockType())
        );
    }

    void saveStreetTest(HouseData houseData) {
        Street street = streetRepository.findByStreetFiasId(checkUUID(houseData.getStreetFiasId()));

        Assertions.assertAll("Test save streets entity",
                () -> Assertions.assertEquals(street.getStreet(), houseData.getStreet()),
                () -> Assertions.assertEquals(convertUUID(street.getStreetFiasId()), houseData.getStreetFiasId()),
                () -> Assertions.assertEquals(street.getStreetType(), houseData.getStreetType())
        );
    }

    void saveSettlementTest(HouseData houseData) {
        Settlement settlement = settlementRepository.findBySettlementFiasId(checkUUID(houseData.getSettlementFiasId()));

        if (settlement == null) {
            Assertions.assertNull(houseData.getSettlement());
            Assertions.assertNull(houseData.getSettlementType());
            Assertions.assertNull(houseData.getSettlementFiasId());
        } else {
            Assertions.assertAll("Test save settlements entity",
                    () -> Assertions.assertEquals(settlement.getSettlement(), houseData.getSettlement()),
                    () -> Assertions.assertEquals(convertUUID(settlement.getSettlementFiasId()), houseData.getSettlementFiasId()),
                    () -> Assertions.assertEquals(settlement.getSettlementType(), houseData.getSettlementType())
            );
        }
    }

    void saveLocalityTest(HouseData houseData) {
        Locality locality = localityRepository.findByLocalityFiasId(checkUUID(houseData.getCityFiasId()));

        Assertions.assertAll("Test save localities entity",
                () -> Assertions.assertEquals(locality.getLocality(), houseData.getCity()),
                () -> Assertions.assertEquals(locality.getLocalityType(), houseData.getCityType()),
                () -> Assertions.assertEquals(convertUUID(locality.getLocalityFiasId()), houseData.getCityFiasId()),
                () -> Assertions.assertEquals(locality.getLocalityKladrId(), houseData.getCityKladrId())
        );
    }

    void saveRegionTest(HouseData houseData) {
        Region region = regionRepository.findByRegionFiasId(checkUUID(houseData.getRegionFiasId()));

        Assertions.assertAll("Test save regions entity",
                () -> Assertions.assertEquals(region.getRegion(), houseData.getRegion()),
                () -> Assertions.assertEquals(convertUUID(region.getRegionFiasId()), houseData.getRegionFiasId())
        );

        regionRepository.delete(region);
    }

    void saveCountryTest(HouseData houseData) {
        Country country = countryRepository.findByCountryIsoCode(houseData.getCountryIsoCode());

        Assertions.assertAll("Test save countries entity",
                () -> Assertions.assertEquals(country.getCountry(), houseData.getCountry()),
                () -> Assertions.assertEquals(country.getCountryIsoCode(), houseData.getCountryIsoCode())
        );

        countryRepository.delete(country);
    }

    private String convertUUID(UUID uuid) {
        return uuid == null ? null : uuid.toString();
    }

    private String isUUID(String uuidString) {
        if (uuidString != null && uuidString.length() != 36)
            return null;
        return uuidString;
    }

    private UUID checkUUID(String uuid) {
        if (uuid == null)
            return null;
        return UUID.fromString(uuid).toString().equals(uuid) ? UUID.fromString(uuid) : null;
    }
}
