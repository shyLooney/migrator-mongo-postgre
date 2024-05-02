package com.pet.migrator.postgres.dto;

import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.postgres.model.*;
import com.pet.migrator.postgres.repository.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Component
@AllArgsConstructor
public class HouseDTO {
    private CountryRepository countryRepository;
    private RegionRepository regionRepository;
    private CityRepository cityRepository;
    private SettlementRepository settlementRepository;
    private StreetRepository streetRepository;
    private HouseRepository houseRepository;
    private ResultRepository resultRepository;
    private EntranceRepository entranceRepository;

    public void save(HouseDoc houseDoc) {
        HouseObjectFactory houseObjectFactory = new DefaultHouseObjectFactory(houseDoc);
        List<Long> list = new ArrayList<>();

        Country country = houseObjectFactory.createCountry();
        Region region = houseObjectFactory.createRegion();
        City city = houseObjectFactory.createCity();
        Settlement settlement = houseObjectFactory.createSettlement();
        Street street = houseObjectFactory.createStreet();
        House house = houseObjectFactory.createHouse();
        Result result = houseObjectFactory.createResult();

        countryRepository.save(country)
                .flatMap(country1 -> {
                    list.add(country1.getId());

                    if (region == null) {
                        Region temp = new Region();
                        temp.setId(1L);
                        return Mono.just(temp);
                    }
                    else
                        return regionRepository.save(region);
                })
                .flatMap(region1 -> {
                    list.add(region1.getId());

                    if (city == null) {
                        City temp = new City();
                        temp.setId(1L);
                        return Mono.just(temp);
                    }
                    else
                        return cityRepository.save(city);
                })
                .flatMap(city1 -> {
                    list.add(city1.getId());

                    if (settlement == null) {
                        Settlement temp = new Settlement();
                        temp.setId(1L);
                        return Mono.just(temp);
                    }
                    else
                        return settlementRepository.save(settlement);
                })
                .flatMap(settlement1 -> {
                    list.add(settlement1.getId());

                    if (street == null) {
                        Street temp = new Street();
                        temp.setId(1L);
                        return Mono.just(temp);
                    }
                    else
                        return streetRepository.save(street);
                })
                .flatMap(street1 -> {
                    list.add(street1.getId());

                    if (house == null) {
                        House temp = new House();
                        temp.setId(1L);
                        return Mono.just(temp);
                    }
                    else
                        return houseRepository.save(house);
                })
                .flatMap(house1 -> {
                    list.add(house1.getId());

                    System.out.println(list);
                    Long[] way = new Long[list.size()];
                    for (int i = 0; i < way.length; i++)
                        way[i] = list.get(i);
                    System.out.println(Arrays.toString(way));
                    result.setWayResult(way);

                    return resultRepository.save(result);
                })
                .flatMap(resultObj -> {
                    List<Entrance> entrance = houseObjectFactory.createEntrance(resultObj.getId());

                    for (var item : entrance) {
                        item.setResultId(resultObj.getId());
                        entranceRepository.save(item).subscribe();
                    }

                    return Mono.just(resultObj);
                })
                .subscribe();
    }
}
