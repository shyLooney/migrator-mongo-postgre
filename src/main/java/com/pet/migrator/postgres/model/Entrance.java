package com.pet.migrator.postgres.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entrance {
    @Id
    private Long id;
    private Integer number;
    private Integer fromEn;
    private Integer toEn;
    private Long resultId;
}
