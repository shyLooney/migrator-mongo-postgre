package com.pet.migrator.postgres.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    private Long id;
    private Integer fiasLevel;
    private Long[] wayResult;
    private Integer porchesNumber;
    private String address;
}
