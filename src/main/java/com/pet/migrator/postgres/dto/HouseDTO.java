package com.pet.migrator.postgres.dto;

import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.postgres.model.*;
import com.pet.migrator.postgres.repository.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
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
    public Mono<Void> save(HouseDoc houseDoc) {
        HouseObjectFactory factory = new DadataAdditionProxy(houseDoc);

        Country countryObj = factory.createCountry();
        log.info(houseDoc.toString());
        log.info(countryObj.toString());


        return countryRepository
                .findByCountryIsoCode(countryObj.getCountryIsoCode())
                .switchIfEmpty(countryRepository.save(countryObj))
                .flatMap(country -> {
                    Region regionObj = factory.createRegion(country.getId());
                    log.info(regionObj.toString());

                    return regionRepository.findByRegionFiasId(regionObj.getRegionFiasId())
                            .switchIfEmpty(regionRepository.save(regionObj));
                })
                .flatMap(region -> {
                    Locality localityObj = factory.createLocality(region.getId());

                    if (localityObj != null)
                        log.info(localityObj.toString());
                    else {
                        Locality localityTemp = new Locality();
                        localityTemp.setRegionId(region.getId());

                        return localityRepository.save(localityTemp);
                    }

                    return localityRepository.findByLocalityFiasId(localityObj.getLocalityFiasId())
                            .switchIfEmpty(localityRepository.save(localityObj));
                })
                .flatMap(locality -> {
                    Settlement settlementObj = factory.createSettlement(locality.getId());

                    if (settlementObj != null) {
                        return settlementRepository.findBySettlementFiasId(settlementObj.getSettlementFiasId())
                                .switchIfEmpty(settlementRepository.save(settlementObj))
                                .flatMap(settlement -> {
                                    Street streetObj = factory.createStreet(settlement.getId(), locality.getId());
                                    if (streetObj == null) {
                                        Street streetTemp = new Street();
                                        streetTemp.setId(null);
                                        return Mono.just(streetTemp);
                                    }

                                    log.info(streetObj.toString());

                                    return streetRepository.findByStreetFiasId(streetObj.getStreetFiasId())
                                            .switchIfEmpty(streetRepository.save(streetObj));
                                });
                    } else {
                        return Mono.just(locality)
                                .flatMap(locality1 -> {
                                    Street streetObj = factory.createStreet(null, locality.getId());

                                    if (streetObj == null) {
                                        Street streetTemp = new Street();
                                        streetTemp.setLocalityId(locality1.getId());

                                        return streetRepository.save(streetTemp);
                                    }

                                    log.info(streetObj.toString());
                                    return streetRepository.findByStreetFiasId(streetObj.getStreetFiasId())
                                            .switchIfEmpty(streetRepository.save(streetObj));
                                });
                    }
                })
                .flatMap(street -> {
                    House houseObj = factory.createHouse(street.getId());
                    log.info(houseObj.toString());

                    return houseRepository.findByHouseFiasId(houseObj.getHouseFiasId())
                            .switchIfEmpty(houseRepository.save(houseObj));
                })
                .flatMapMany(ignore -> {
                    Result resultObj = factory.createResult();

                    return resultRepository.findByAddress(resultObj.getAddress())
                            .switchIfEmpty(resultRepository.save(resultObj));
                    // save entrance:
//                            .flatMapMany(result -> {
//                                List<Entrance> entrance = factory.createEntrance(result.getId());
//                                List<Mono<Entrance>> monoList = new ArrayList<>();
//                                if (entrance != null) {
//                                    for (Entrance entranceObj : entrance) {
//                                        log.info(entranceObj.toString());
//                                        monoList.add(entranceRepository.save(entranceObj));
//                                    }
//                                    return Flux.merge(monoList);
//                                }
//                                else
//                                    return Mono.just(result);
//                            });
                })
                .then();
    }


}
