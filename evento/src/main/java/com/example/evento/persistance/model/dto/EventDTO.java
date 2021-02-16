package com.example.evento.persistance.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EventDTO {

    private Long id;
    private String name;
    private String startTime;
    private String endTime;
    private Boolean freeEntrance;
    private String city;
    private String citySize;
    private String county;
    private String region;
    private Boolean cityActive;

}
