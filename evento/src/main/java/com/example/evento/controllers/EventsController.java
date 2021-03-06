package com.example.evento.controllers;

import com.example.evento.persistance.converter.EventConverter;
import com.example.evento.persistance.model.Event;
import com.example.evento.persistance.model.dto.EventDTO;
import com.example.evento.persistance.model.dto.EventRequest;
import com.example.evento.services.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/events")
public class EventsController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventConverter eventConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(EventsController.class);

    @GetMapping(value = "/{id}")
    public Event findOne(@PathVariable Long id) {
        LOGGER.info("In eventsController, findOne with id: {}", id);
        return eventService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/getAllEvents")
    public List<Event> getAllEvents() {
        LOGGER.info("In eventsController, getAllEvents");
        return eventService.getAll();
    }

    @GetMapping(value = "/getEvents")
    public List<EventDTO> getEvents() {
        LOGGER.info("In eventsController, getEvents");
        return eventConverter.convertEvent();
    }

    @PostMapping(value = "/save")
    public List<EventDTO> create(@RequestBody Map<String, String> event) {
        LOGGER.info("In eventsController, create with event: {}", event);
        eventService.save(event);
        return eventConverter.convertEvent();
    }

    @GetMapping(value = "/search/{name}")
    public List<String> searchEventsByName(@PathVariable String name) {
        LOGGER.info("In eventsController, searchEventsByName with name: {}", name);
        return eventService.searchEventsByName(name);
    }

    @PostMapping(value = "/search/event")
    public List<EventDTO> getSearchedEvents(@RequestBody EventRequest eventRequest) {
        LOGGER.info("In eventsController, getSearchedEvents with eventRequest: {}", eventRequest);
        return eventService.searchEventsByForm(eventRequest);
    }

    @PostMapping(value = "/delete" )
    public ResponseEntity deleteEvent(@RequestBody Map<String, String> eventRequest) {
        LOGGER.info("In eventsController, getSearchedEvents with eventRequest: {}", eventRequest);

        try {
            eventService.deleteEvent(eventRequest);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/edit", consumes = "application/json;charset=UTF-8")
    public ResponseEntity editEvent(@RequestBody Map<String, String> eventRequest){
        LOGGER.info("In eventsController, editEvent: {}", eventRequest);

        try {
            eventService.editEvent(eventRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
