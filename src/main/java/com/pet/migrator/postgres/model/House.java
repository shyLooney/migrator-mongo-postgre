package com.pet.migrator.postgres.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {
    @Id
    private Long id;
    private UUID houseFiasId;
    private String houseType;
    private String house;
    private String blockType;
    private String block;
    private Long streetId;
}