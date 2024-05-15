package com.pet.migrator.postgres.dto;

import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.postgres.model.*;
import com.pet.migrator.postgres.repository.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Service
@AllArgsConstructor
@Slf4j
public class HouseDTO {
    private CountryRepository countryRepository;
    private RegionRepository regionRepository;
    private LocalityRepository localityRepository;
    private SettlementRepository settlementRepository;
    private StreetRepository streetRepository;
    private HouseRepository houseRepository;
    private ResultRepository resultRepository;
    private EntranceRepository entranceRepository;

    @Transactional
    public void save(HouseDoc houseDoc) {
        HouseObjectFactory factory = new DefaultHouseObjectFactory(houseDoc);

        log.info(houseDoc.toString());
        Country country = factory.createCountry();

        log.info(country.toString());

        Country countryTemp = countryRepository.findByCountryIsoCode(country.getCountryIsoCode());
        if (countryTemp == null) {
            countryRepository.save(country);
        }
        else
            country = countryTemp;

        Region region = factory.createRegion(country);
        Region regionTemp = regionRepository.findByRegionFiasId(region.getRegionFiasId());
        if (regionTemp == null) {
            regionRepository.save(region);
        }
        else
            region = regionTemp;

        log.info(region.toString());
        Locality locality = factory.createLocality(region);
        log.info(locality.toString());
        Locality localityTemp = localityRepository.findByLocalityFiasId(locality.getLocalityFiasId());
        if (localityTemp == null) {
            localityRepository.save(locality);
        }
        else
            locality = localityTemp;

        log.info(locality.toString());
        Settlement settlement = factory.createSettlement(locality);
        if (settlement != null) {
            Settlement settlementTemp = settlementRepository.findBySettlementFiasId(settlement.getSettlementFiasId());
            if (settlementTemp == null) {
                settlementRepository.save(settlement);
            }
            else
                settlement = settlementTemp;
        }

        if (settlement != null)
            log.info(settlement.toString());
        Street street = factory.createStreet(settlement, locality);
        Street streetTemp = streetRepository.findByStreetFiasId(street.getStreetFiasId());
        if (streetTemp == null) {
            streetRepository.save(street);
        }
        else
            street = streetTemp;

        log.info(street.toString());
        House house = factory.createHouse(street);
        if (house.getHouseFiasId() != null) {
            House houseTemp = houseRepository.findByHouseFiasId(house.getHouseFiasId());
            if (houseTemp == null) {
                houseRepository.save(house);
            }
            else
                house = houseTemp;
        }
        else
            houseRepository.save(house);

        log.info(house.toString());


        Result result = factory.createResult();
        Result resultTemp = resultRepository.findByAddress(result.getAddress());
        if (resultTemp == null) {
            resultRepository.save(result);
        }
        else {
            result = resultTemp;
        }

        log.info(result.toString());
        List<Entrance> entrance = factory.createEntrance(result);

        if (entrance != null) {
            log.info(entrance.toString());
            entranceRepository.saveAll(entrance);
        }


    }
}
