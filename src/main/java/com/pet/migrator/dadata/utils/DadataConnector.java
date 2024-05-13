package com.pet.migrator.dadata.utils;

import com.pet.migrator.dadata.model.DataDadata;
import com.pet.migrator.dadata.model.Suggestion;
import com.pet.migrator.dadata.model.SuggestionList;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class DadataConnector {
    private static WebClient webClientFias;
    private static WebClient webClientAddress;

    private DadataConnector() {
    }

    public static Mono<SuggestionList> getDataByFiasId(String query) {
        if (webClientFias == null) {
            webClientFias = WebClient.create("http://suggestions.dadata.ru");
        }

        return webClientFias
                .post()
                .uri("/suggestions/api/4_1/rs/findById/address")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Token 00b8b28695d8b4ea3494444755bc186dde718605")
                .header("X-Secret", "ccf8e61d0850592bb4d3821290eef4cfcd8b1208")
                .bodyValue("{ \"query\": \"" + query + "\" }")
                .retrieve()
                .bodyToMono(SuggestionList.class);
    }

    public static DataDadata[] getDataByAddress(String query) {
        if (webClientAddress == null) {
            webClientAddress = WebClient.create("https://cleaner.dadata.ru");
        }

        return webClientAddress
                .post()
                .uri("/api/v1/clean/address")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Token 00b8b28695d8b4ea3494444755bc186dde718605")
                .header("X-Secret", "ccf8e61d0850592bb4d3821290eef4cfcd8b1208")
                .bodyValue("[ \"" + query + "\" ]")
                .retrieve()
                .bodyToMono(DataDadata[].class)
                .block();
    }
}
