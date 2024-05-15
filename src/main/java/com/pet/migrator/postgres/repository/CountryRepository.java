package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Long> {
    Country findByCountryIsoCode(String countryIsoCode);
    Boolean existsByCountryIsoCode(String countryIsoCode);
}
