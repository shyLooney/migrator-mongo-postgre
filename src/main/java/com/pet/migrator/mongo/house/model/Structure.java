package com.pet.migrator.mongo.house.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Structure {
    private List<Entrance> entrances;
}
