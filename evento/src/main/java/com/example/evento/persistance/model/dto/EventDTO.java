package com.example.evento.persistance.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EventDTO {

    private String name;
    private Timestamp startTime;
    private Timestamp endTime;
    private Boolean freeEntrance;
    private String city;
    private String citySize;
    private String county;
    private String region;
    private Boolean cityActive;

}
