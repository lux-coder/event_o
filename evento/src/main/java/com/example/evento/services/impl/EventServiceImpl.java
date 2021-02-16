package com.example.evento.services.impl;

import com.example.evento.persistance.converter.EventConverter;
import com.example.evento.persistance.model.Event;
import com.example.evento.persistance.model.dto.EventDTO;
import com.example.evento.persistance.model.dto.EventRequest;
import com.example.evento.persistance.repository.CityRepository;
import com.example.evento.persistance.repository.EventRepository;
import com.example.evento.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventConverter eventConverter;

    @Autowired
    private CityRepository cityRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    @Override
    public Optional<Event> findById(Long id) {
        LOGGER.info("In event service, findById with id: {}", id);
        return eventRepository.findById(id);
    }

    @Override
    public void save(Map<String, String> event) {
        LOGGER.info("In event service, save: {}", event);

        if(event.get("eventEndDate") != null) {
            eventRepository.saveEvent(event.get("event"), event.get("eventStartDate").replace("T", " "),
                    event.get("eventEndDate").replace("T", " "),
                    Boolean.valueOf(event.get("freeEntrance")), cityRepository.getCityId(event.get("cities$")));
        }else {
            eventRepository.saveEventWithoutEnd(event.get("event"), event.get("eventStartDate").replace("T", " "),
                    Boolean.valueOf(event.get("freeEntrance")), cityRepository.getCityId(event.get("cities$")));
        }
        LOGGER.info("saved event");
    }

    @Override
    public List<Event> getAll() {
        LOGGER.info("In event service, getAll");
        return StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getAllInActiveCity() {
        LOGGER.info("In event service, getAllInActiveCity");
        return eventRepository.findAllInActiveCity().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> searchEventsByName(String name) {
        LOGGER.info("In event service, searchEventsByName");
        return eventRepository.searchByName(name);
    }

    @Override
    public List<EventDTO> searchEventsByForm(EventRequest eventRequest) {
        LOGGER.info("In event service, searchEventsByForm");

        LOGGER.info("Event request - regions: {}", eventRequest.getRegions$());
        LOGGER.info("Event request - counties: {}", eventRequest.getCounty$());
        LOGGER.info("Event request - city: {}", eventRequest.getCity$());

        List<Event> preview = new ArrayList<>();

        if(eventRequest.getCity$() == null) {
            if(eventRequest.getCounty$() == null && eventRequest.getRegions$() != null) {
                preview = getByRegion(eventRequest.getRegions$());
            } else if (eventRequest.getCounty$() != null) {
                preview = getByCounty(eventRequest.getCounty$());
            }
        } else if (eventRequest.getRegions$() == null && eventRequest.getCounty$() == null && eventRequest.getCity$() != null){
            preview = getByCity(eventRequest.getCity$());
        }


        if (preview.isEmpty() && eventRequest.getEventName() != null) {
            LOGGER.info("event name: {}", eventRequest.getEventName());
            preview = eventRepository.listByName(eventRequest.getEventName());
        }

        if (preview.isEmpty() && eventRequest.getEventStartDate() != null) {
            LOGGER.info("event start date: {}", eventRequest.getEventStartDate());
            preview = eventRepository.getByStartDate(correctTimestamp(eventRequest.getEventStartDate()));
        }

        if (preview.isEmpty() && eventRequest.getEventStartDate() == null && eventRequest.getEventEndDate() != null) {
            LOGGER.info("event end date: {}", eventRequest.getEventEndDate());
            String time = correctTimestamp(eventRequest.getEventEndDate());
            LOGGER.info("TIME END: {}", time);
            preview = eventRepository.getByEndDate(time);
        }

        if (preview.isEmpty() && eventRequest.getEventStartDate() != null && eventRequest.getEventEndDate() != null) {
            LOGGER.info("date between: {} - {}", eventRequest.getEventStartDate(), eventRequest.getEventEndDate());
            preview = eventRepository.getByDateBetween(correctTimestamp(eventRequest.getEventStartDate()),
                    correctTimestamp(eventRequest.getEventEndDate()));
        }

        if (preview.isEmpty() && eventRequest.getEntrance() != null) {
            LOGGER.info("entrance free: {}", eventRequest.getEntrance());
            preview = eventRepository.getByEntrance(eventRequest.getEntrance());
        }

        LOGGER.info("EVENTS: {}", preview);

        return eventConverter.convertSpecific(preview);
    }

    @Override
    public void deleteEvent(Map<String, String> eventRequest) {
        LOGGER.info("In eventService, deleteEvent: {}", eventRequest);
        eventRepository.deleteById(eventRepository.getId(eventRequest.get("name")));
        LOGGER.info("DELETED");
    }

    @Override
    public void editEvent(Map<String, String> eventRequest) {
        LOGGER.info("In eventService, editEvent: {}", eventRequest);
        eventRepository.editEvent(eventRequest.get("name"), eventRequest.get("startTime"), eventRequest.get("endTime"),
                Boolean.valueOf(eventRequest.get("freeEntrance")), cityRepository.getCityId(eventRequest.get("city")),
                Long.valueOf(eventRequest.get("id")));
    }

    private List<Event> getByCity(List<String> city$) {
        String[] cities = city$.toArray(new String[0]);
        LOGGER.info("CITY: {}", cities);
        return eventRepository.findByCity(cities);
    }

    private List<Event> getByCounty(List<String> county$) {
        String[] counties = county$.toArray(new String[0]);
        LOGGER.info("COUNTIES: {}", counties);
        return eventRepository.findByCounty(counties);
    }

    private List<Event> getByRegion(List<String> regions$) {
        String[] regions = regions$.toArray(new String[0]);
        LOGGER.info("REGIONS: {}", regions);
        return eventRepository.findByRegion(regions);
    }

    private String correctTimestamp(String dateTime) {
        String time = dateTime.substring(0,11);
        String hour = dateTime.substring(11,13);
        String minute = dateTime.substring(14,16);
        String timestamp = time + (Integer.parseInt(hour) + 1) + ":" + minute + ":00.000Z";
        LOGGER.info("TIMESTAMP: {}", timestamp);
        return timestamp;
    }
}
