package com.example.evento.persistance.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "city_size")
public class CitySize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Long id;

    @NonNull
    @Column(name = "size_value")
    private String value;

    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    @Column(name = "city_id")
    private Long city;

    public CitySize() { }

    public CitySize(String value, Long cityId) {
        this.value = value;
        this.city = cityId;
    }

}
