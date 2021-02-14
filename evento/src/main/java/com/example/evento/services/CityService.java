package com.example.evento.services;

import com.example.evento.persistance.model.City;
import com.example.evento.persistance.model.dto.CityDTO;

import java.util.List;
import java.util.Optional;

public interface CityService {

    Optional<City> findById(Long id);

    List<CityDTO> getAllCities();

    List<CityDTO> getCities(String requestedCounties);
}
