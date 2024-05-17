package com.pet.migrator.config;

import com.mongodb.client.model.changestream.ChangeStreamDocument;
import com.pet.migrator.mongo.house.model.HouseDoc;
import com.pet.migrator.postgres.dto.HouseDTO;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.*;

@Configuration
@Slf4j
public class RuntimeMigrator {
    @Bean
    public CommandLineRunner changeStreamTest(MongoTemplate template, HouseDTO houseDTO) {
        return args -> {
            MessageListenerContainer container = new DefaultMessageListenerContainer(template);
            container.start();

            MessageListener<ChangeStreamDocument<Document>, HouseDoc> listener = item -> {
                if (item.getBody() != null)
                    log.info(item.getBody().toString());
                if (item.getBodyBeforeChange() != null)
                    log.info(item.getBodyBeforeChange().toString());
                log.info(item.getProperties().toString());
                houseDTO.save(item.getBody());
            };

            ChangeStreamRequest.ChangeStreamRequestOptions options = new ChangeStreamRequest.ChangeStreamRequestOptions("demo", "houseDoc", ChangeStreamOptions.empty());

            Subscription subscription = container.register(new ChangeStreamRequest<>(listener, options), HouseDoc.class);


//			container.stop();
        };
    }
}
