package com.example.evento.controllers;

import com.example.evento.persistance.model.City;
import com.example.evento.persistance.model.CitySize;
import com.example.evento.persistance.model.OrganizationalUnit;
import com.example.evento.persistance.model.dto.CityDTO;
import com.example.evento.persistance.model.dto.OrganizationDTO;
import com.example.evento.persistance.repository.CitySizeRepository;
import com.example.evento.persistance.repository.OrganizationalUnitRepository;
import com.example.evento.services.CityService;
import com.example.evento.services.OrganizationalUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/organizations")
public class OrganizationsController {

    @Autowired
    private OrganizationalUnitService organizationalUnitService;

    @Autowired
    private CityService cityService;

    @Autowired
    private OrganizationalUnitRepository organizationalUnitRepository;

    @Autowired
    private CitySizeRepository citySizeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationsController.class);

    @GetMapping(value = "/getRegions")
    public List<String> getOrganizationalUnits() {
        LOGGER.info("In OrganizationsController, getRegions");
        return organizationalUnitService.getRegions().stream().map(OrganizationalUnit::getName).collect(Collectors.toList());
    }

    @GetMapping(value = "/getAllRegions")
    public List<OrganizationalUnit> getAllRegions() {
        LOGGER.info("In OrganizationsController, getAllRegions");
        return organizationalUnitService.getRegions();
    }

    @PostMapping(value = "/region/save", consumes = "application/json;charset=UTF-8")
    public List<OrganizationalUnit> saveRegion(@RequestBody Map<String, String> requestRegion) {
        LOGGER.info("In OrganizationsController, saveRegion: {}", requestRegion);
        organizationalUnitService.saveRegion(requestRegion);

        return organizationalUnitService.getRegions();
    }

    @GetMapping(value = "/getCountiesFull")
    public List<OrganizationDTO> getCountiesFull() {
        LOGGER.info("In OrganizationsController, getCounties");
        return organizationalUnitService.getCountiesFull();
    }

    @GetMapping(value = "/getAllCounties")
    public List<String> getAllCounties() {
        LOGGER.info("In OrganizationsController, getCounties");
        return organizationalUnitService.getAllCounties().stream().map(OrganizationalUnit::getName).collect(Collectors.toList());
    }

    @PostMapping(value = "/getCounties", consumes = "text/plain;charset=UTF-8")
    public List<String> getCounties(@RequestBody String requestedRegions) {
        LOGGER.info("In OrganizationsController, getCounties");
        LOGGER.info("requestedRegions: {}", requestedRegions);
        return organizationalUnitService.getCounties(requestedRegions).stream().map(OrganizationalUnit::getName).collect(Collectors.toList());
    }

    @PostMapping(value = "/county/save", consumes = "application/json;charset=UTF-8")
    public List<OrganizationDTO> saveCounty(@RequestBody Map<String, String> requestCounty) {
        LOGGER.info("In OrganizationsController, saveCounty: {}", requestCounty);
        organizationalUnitService.saveCounty(requestCounty);

        return organizationalUnitService.getCountiesFull();
    }

    @PostMapping(value = "/city/save", consumes = "application/json;charset=UTF-8")
    public List<CityDTO> saveCity(@RequestBody Map<String, String> requestCity) {
        LOGGER.info("In OrganizationsController, saveCity: {}", requestCity);
        cityService.saveCity(requestCity);

        LOGGER.info("SAVEEEED!!!!");
        return cityService.getAllCities();
    }

    @GetMapping(value = "/getAllCities")
    public List<CityDTO> getAllCities() {
        LOGGER.info("In OrganizationsController, getAllCities");
        return cityService.getAllCities();
    }

    @PostMapping(value = "/getCities", consumes = "text/plain;charset=UTF-8")
    public List<CityDTO> getCities(@RequestBody String requestedCounties) {
        LOGGER.info("In OrganizationsController, getACities");

        List<CityDTO> cityDTOS = cityService.getCities(requestedCounties);
        cityDTOS.forEach(c -> LOGGER.info(c.toString()));

        return cityDTOS;
    }
}
