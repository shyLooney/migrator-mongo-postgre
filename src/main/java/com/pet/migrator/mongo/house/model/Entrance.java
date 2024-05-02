package com.pet.migrator.mongo.house.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Entrance {
    private Integer number;
    private Integer from;
    private Integer to;
}
