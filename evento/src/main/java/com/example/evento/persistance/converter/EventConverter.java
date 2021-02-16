package com.example.evento.persistance.converter;

import com.example.evento.persistance.model.City;
import com.example.evento.persistance.model.CitySize;
import com.example.evento.persistance.model.Event;
import com.example.evento.persistance.model.dto.EventDTO;
import com.example.evento.services.CityService;
import com.example.evento.services.CitySizeService;
import com.example.evento.services.EventService;
import com.example.evento.services.OrganizationalUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventConverter {

    @Autowired
    private EventService eventService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CitySizeService citySizeService;

    @Autowired
    private OrganizationalUnitService organizationalUnitService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EventConverter.class);

    public List<EventDTO> convertEvent() {
        LOGGER.info("In convertEvent");

        List<Event> events = eventService.getAllInActiveCity();
        List<EventDTO> eventDTOList = new ArrayList<>();

        for(Event event: events) {
            EventDTO eventDTO = new EventDTO();
            Long cityId = event.getCity();
            City city = cityService.findById(cityId).orElseThrow(RuntimeException::new);
            CitySize citySize = citySizeService.findByCityId(cityId).orElseThrow(RuntimeException::new);
            eventDTO.setId(event.getId());
            eventDTO.setCity(city.getName());
            eventDTO.setCitySize(citySize.getValue());
            eventDTO.setCityActive(citySize.getActive());
            eventDTO.setCounty(organizationalUnitService.findById(city.getOrganizationalUnit()).orElseThrow(RuntimeException::new).getName());
            eventDTO.setRegion(organizationalUnitService.getOrType(city.getOrganizationalUnit()));
            eventDTO.setName(event.getName());
            eventDTO.setStartTime(event.getStartTime());
            eventDTO.setEndTime(event.getEndTime());
            eventDTO.setFreeEntrance(event.getFreeEntrance());

            eventDTOList.add(eventDTO);
        }

        return eventDTOList;
    }

    public List<EventDTO> convertSpecific(List<Event> eventsList) {

        List<EventDTO> eventDTOList = new ArrayList<>();

        for(Event event: eventsList) {
            EventDTO eventDTO = new EventDTO();
            Long cityId = event.getCity();
            City city = cityService.findById(cityId).orElseThrow(RuntimeException::new);
            CitySize citySize = citySizeService.findByCityId(cityId).orElseThrow(RuntimeException::new);
            eventDTO.setCity(city.getName());
            eventDTO.setCitySize(citySize.getValue());
            eventDTO.setCityActive(citySize.getActive());
            eventDTO.setCounty(organizationalUnitService.findById(city.getOrganizationalUnit()).orElseThrow(RuntimeException::new).getName());
            eventDTO.setRegion(organizationalUnitService.getOrType(city.getOrganizationalUnit()));
            eventDTO.setName(event.getName());
            eventDTO.setStartTime(event.getStartTime());
            eventDTO.setEndTime(event.getEndTime());
            eventDTO.setFreeEntrance(event.getFreeEntrance());

            eventDTOList.add(eventDTO);
        }


        return eventDTOList;
    }
}
