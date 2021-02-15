package com.example.evento.services.impl;

import com.example.evento.persistance.converter.CityConverter;
import com.example.evento.persistance.model.City;
import com.example.evento.persistance.model.CitySize;
import com.example.evento.persistance.model.dto.CityDTO;
import com.example.evento.persistance.repository.CityRepository;
import com.example.evento.persistance.repository.CitySizeRepository;
import com.example.evento.persistance.repository.OrganizationalUnitRepository;
import com.example.evento.services.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityConverter cityConverter;

    @Autowired
    private CitySizeRepository citySizeRepository;

    @Autowired
    private OrganizationalUnitRepository organizationalUnitRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Override
    public Optional<City> findById(Long id) {
        LOGGER.info("In cityService, findById");
        return cityRepository.findById(id);
    }

    @Override
    public List<CityDTO> getAllCities() {
        LOGGER.info("In cityService, getAll");

        List<CityDTO> cityDTOS = cityConverter.convertCity(cityRepository.getAllCities());

        for (CityDTO c: cityDTOS) {
            c.setOrganizationUnit(organizationalUnitRepository.getCountyName(c.getOrUnitId().longValue()));
        }

        return cityDTOS;
    }

/*    @Override
    public List<City> getAllCities() {
        LOGGER.info("In cityService, getAll");
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }*/

    @Override
    public List<CityDTO> getCities(String requestedCounties) {
        LOGGER.info("In CityService, getCities with requestedCounties:{}", requestedCounties);

        String countiesReq = requestedCounties.substring(1, requestedCounties.length()-1).replaceAll("\"", "");
        List<String> countiesList = new ArrayList<>(Arrays.asList(countiesReq.split(",")));
        String[] counties = countiesList.toArray(new String[0]);

        return cityConverter.convertCity(cityRepository.getCities(counties));
    }

    @Override
    public void saveCity(Map<String, String> requestCity) {
        LOGGER.info("In CityService, saveCity: {}", requestCity);

        City city = new City(requestCity.get("city"), organizationalUnitRepository.getCountyId(requestCity.get("counties$")));
        city = cityRepository.save(city);

        CitySize citySize = new CitySize();
        citySize.setCity(city.getId());
        citySize.setValue(requestCity.get("size"));
        citySize.setActive(Boolean.parseBoolean(requestCity.get("active")));

        citySizeRepository.save(citySize);
    }
}
