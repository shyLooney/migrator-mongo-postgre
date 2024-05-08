package com.pet.migrator.postgres.repository;

import com.pet.migrator.postgres.model.Country;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface CountryRepository extends R2dbcRepository<Country, Long> {
    Mono<Country> findByCountryIsoCode(String countryIsoCode);
}
