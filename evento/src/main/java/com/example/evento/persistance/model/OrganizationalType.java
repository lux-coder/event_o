package com.example.evento.persistance.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "or_type")
public class OrganizationalType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    @Column(name = "type_name", unique = true)
    private String name;

    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    @Column(name = "unit_id")
    private Long unit;

    public OrganizationalType() { }

    public OrganizationalType(String name, Long unitId) {
        this.name = name;
        this.unit = unitId;
    }

    public OrganizationalType(String name, Long unitId, Boolean active) {
        this.name = name;
        this.unit = unitId;
        this.active = active;
    }
}
