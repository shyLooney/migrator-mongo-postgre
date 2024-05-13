package com.pet.migrator.dadata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
public class SuggestionList {
    @Id
    @JsonIgnore
    private Long id;
    private List<Suggestion> suggestions;

}
