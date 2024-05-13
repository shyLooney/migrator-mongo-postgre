package com.pet.migrator.dadata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"value, unrestricted_value, data"})
public class Suggestion {
    @Id
    @JsonIgnore
    private Long id;
    private String value;
    @JsonProperty("unrestricted_value")
    private String unrestrictedValue;
    @JsonIgnore
    private Long dadataId;
    private UUID uuid = UUID.randomUUID();
    @Transient
    private DataDadata data;
}
