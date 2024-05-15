package com.pet.migrator.mongo.house.model;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Entrance {
    private Integer number;
    private Integer from;
    private Integer to;
}
