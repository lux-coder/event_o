package com.example.evento.services;

import com.example.evento.persistance.model.Event;
import com.example.evento.persistance.model.dto.EventDTO;
import com.example.evento.persistance.model.dto.EventRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EventService {

    Optional<Event> findById(Long id);

    void save(Map<String, String> event);

    List<Event> getAll();

    List<Event> getAllInActiveCity();

    List<String> searchEventsByName(String name);

    List<EventDTO> searchEventsByForm(EventRequest eventRequest);

    void deleteEvent(Map<String, String> eventRequest);

    void editEvent(Map<String, String> eventRequest);
}
