package com.example.evento.services;

import com.example.evento.persistance.model.CitySize;

import java.util.Optional;

public interface CitySizeService {

    Optional<CitySize> findById(Long id);

    Optional<CitySize> findByCityId(Long id);

    void saveCitySize(CitySize citySize);
}
