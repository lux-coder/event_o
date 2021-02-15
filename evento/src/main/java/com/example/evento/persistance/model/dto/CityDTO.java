package com.example.evento.persistance.model.dto;

import lombok.Data;

@Data
public class CityDTO {

    private Integer cityId;
    private String cityName;
    private Integer orUnitId;
    private String organizationUnit;
    private String sizeValue;
    private Boolean active;

    public CityDTO(Integer cityId, String cityName, Integer orUnitId, String sizeValue, Boolean active) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.orUnitId = orUnitId;
        this.sizeValue = sizeValue;
        this.active = active;
    }

    public CityDTO() { }

    public CityDTO(Integer city_id, String city_name, String size_value) {
        this.cityId = city_id;
        this.cityName = city_name;
        this.sizeValue = size_value;
    }
}
