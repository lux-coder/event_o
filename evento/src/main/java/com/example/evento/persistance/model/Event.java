package com.example.evento.persistance.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    @NonNull
    @Column(name = "event_name")
    private String name;

    @NonNull
    @Column(name = "event_start")
    private String startTime;

    @Column(name = "event_end")
    private String endTime;

    @Column(name = "entrance", columnDefinition = "boolean default true")
    private Boolean freeEntrance;

    @Column(name = "city_id")
    private Long city;

    public Event() { }

    public Event(String name, String startTime, String endTime, Long city) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.city = city;
    }

    public Event(String name, String startTime, String endTime, Boolean freeEntrance, Long city) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.freeEntrance = freeEntrance;
        this.city = city;
    }
}
