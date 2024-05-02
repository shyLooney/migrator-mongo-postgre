package com.pet.migrator.mongo.house.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Data
@Accessors(chain = true)
public class Structure {
    private List<Entrance> entrances;
}
