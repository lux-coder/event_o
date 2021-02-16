package com.example.evento.persistance.repository;

import com.example.evento.persistance.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EventRepositoryIntegrationTest {

    @Autowired
    OrganizationalTypeRepository organizationalTypeRepository;

    @Autowired
    OrganizationalUnitRepository organizationalUnitRepository;

    @Autowired
    CitySizeRepository citySizeRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    EventRepository eventRepository;

    @Test
    public void whenSavingNewEvent_thenSuccess() {

        OrganizationalUnit newOrganizationalUnit = new OrganizationalUnit(randomAlphabetic(15), 1L);
        assertNotNull(organizationalUnitRepository.save(newOrganizationalUnit));

        OrganizationalType newOrganizationalType = new OrganizationalType(randomAlphabetic(5), newOrganizationalUnit.getId());
        assertNotNull(organizationalTypeRepository.save(newOrganizationalType));

        City newCity = new City(randomAlphabetic(10), newOrganizationalUnit.getId());
        assertNotNull(cityRepository.save(newCity));

        CitySize newCitySize = new CitySize(randomAlphabetic(6), newCity.getId());
        assertNotNull(citySizeRepository.save(newCitySize));

        Event newEvent = new Event(randomAlphabetic(20), String.valueOf(LocalDateTime.now()), String.valueOf(LocalDateTime.now()), newCity.getId());
        assertNotNull(eventRepository.save(newEvent));
    }
}
