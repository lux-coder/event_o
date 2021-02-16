package com.example.evento.services;

import com.example.evento.persistance.model.City;
import com.example.evento.persistance.model.dto.CityDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CityService {

    Optional<City> findById(Long id);

    List<CityDTO> getAllCities();

    List<CityDTO> getCities(String requestedCounties);

    void saveCity(Map<String, String> city);

    void deleteCity(Map<String, String> requestedCity);

    void editCity(Map<String, String> requestedCity);
}
