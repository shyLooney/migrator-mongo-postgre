package com.pet.migrator.postgres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Entrance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private Integer fromEn;
    private Integer toEn;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Result result;
}
