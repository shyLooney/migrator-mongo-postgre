package com.pet.migrator.dadata.utils;

import com.pet.migrator.dadata.model.DataDadata;
import com.pet.migrator.dadata.model.SuggestionList;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpHeaders;


public class DadataConnector {
    private static RestTemplate restTemplate = new RestTemplate() ;
    private static RestTemplate restTemplateAddress;
    private static HttpHeaders headers;

    private DadataConnector() {
    }

    public static SuggestionList getDataByFiasId(String query) {
        RequestEntity<String> request = RequestEntity
                .post(URI.create("http://suggestions.dadata.ru/suggestions/api/4_1/rs/findById/address"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Token 00b8b28695d8b4ea3494444755bc186dde718605")
                .header("Authorization", "Token 131a2523bd2fbe6c26d816ac3054f7094f906c7d")
//                .header("X-Secret", "ccf8e61d0850592bb4d3821290eef4cfcd8b1208")
                .header("X-Secret", "95ea1ec1178a940c9c30cfddd495880b39bf7f62")
                .body("{ \"query\": \"" + query + "\" }");

        ResponseEntity<SuggestionList> responseEntity = restTemplate.exchange(request, SuggestionList.class);

        return responseEntity.getBody();
    }

    public static DataDadata[] getDataByAddress(String query) {
        RequestEntity<String> request = RequestEntity
                .post(URI.create("https://cleaner.dadata.ru/api/v1/clean/address"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
//                .header("Authorization", "Token 00b8b28695d8b4ea3494444755bc186dde718605")
                .header("Authorization", "Token 131a2523bd2fbe6c26d816ac3054f7094f906c7d")
//                .header("X-Secret", "ccf8e61d0850592bb4d3821290eef4cfcd8b1208")
                .header("X-Secret", "95ea1ec1178a940c9c30cfddd495880b39bf7f62")
                .body("[ \"" + query + "\" ]");

        try {
            ResponseEntity<DataDadata[]> responseEntity = restTemplate.exchange(request, DataDadata[].class);
            return responseEntity.getBody();
        }
        catch (RestClientException e) {
            e.printStackTrace();
            return null;
        }

    }
}
