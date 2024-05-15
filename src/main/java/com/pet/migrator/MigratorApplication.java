package com.pet.migrator;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.pet.migrator.dadata.utils.DadataConnector;
import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.mongo.house.repository.HouseRepositoryMongo;
import com.pet.migrator.postgres.dto.HouseDTO;
import com.pet.migrator.postgres.model.Country;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.*;

@SpringBootApplication
@Slf4j
public class MigratorApplication {
//	@Bean
//	public CommandLineRunner commandLineRunner(HouseDTO houseDTO, HouseRepositoryMongo houseRepositoryMongo) {
//		return args -> {
//			var list = houseRepositoryMongo.findAll();
//			for (int i = 0; i < list.size(); i++) {
//				houseDTO.save(list.get(i));
//			}
//		};
//	}
//
//	@Bean
//	public CommandLineRunner commandLineRunner2() {
//		return args -> {
//			System.out.println(DadataConnector.getDataByAddress("обл. Тверская, р-н. Пеновский, д. Москва, д. 15"));
//		};
//	}

	@Bean
	public CommandLineRunner changeStreamTest(MongoTemplate template, HouseDTO houseDTO) {
		return args -> {
			MessageListenerContainer container = new DefaultMessageListenerContainer(template);
			container.start();

			MessageListener<ChangeStreamDocument<Document>, HouseDoc> listener = item -> {
				System.out.println(item.getBody());
				System.out.println(item.getBodyBeforeChange());
				System.out.println(item.getProperties());
				houseDTO.save(item.getBody());
			};
			ChangeStreamRequest.ChangeStreamRequestOptions options = new ChangeStreamRequest.ChangeStreamRequestOptions("demo", "houseDoc", ChangeStreamOptions.empty());

			Subscription subscription = container.register(new ChangeStreamRequest<>(listener, options), HouseDoc.class);


			container.stop();
		};
	}

//	@Bean
//	public CommandLineRunner input(HouseRepositoryMongo repository) {
//		return args -> {
//		};
//	}


	public static void main(String[] args) {
		SpringApplication.run(MigratorApplication.class, args);
	}

}
