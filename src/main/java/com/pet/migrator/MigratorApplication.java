package com.pet.migrator;

import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.mongo.house.repository.HouseRepositoryMongo;
import com.pet.migrator.postgres.dto.HouseDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableR2dbcRepositories
public class MigratorApplication {

	@Bean
	public CommandLineRunner commandLineRunner(HouseDTO houseDTO, HouseRepositoryMongo houseRepositoryMongo) {
		return args -> {
			HouseDoc docMono = houseRepositoryMongo.findAll().blockFirst();
			System.out.println(docMono);
			houseDTO.save(docMono);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(MigratorApplication.class, args);
	}

}
