package com.example.evento.persistance.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class EventRequest {

    List<String> regions$;
    List<String> county$;
    List<String> city$;
    String eventName;
    Boolean entrance;
    String eventStartDate;
    String eventEndDate;
}
