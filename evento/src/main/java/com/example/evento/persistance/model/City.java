package com.example.evento.persistance.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @Column(name = "city_name", unique = true)
    @NonNull
    private String name;

    @Column(name = "or_unit_id")
    private Long organizationalUnit;

    public City() { }

    public City(String name, Long organizationalUnit) {
        this.name = name;
        this.organizationalUnit = organizationalUnit;
    }
}
