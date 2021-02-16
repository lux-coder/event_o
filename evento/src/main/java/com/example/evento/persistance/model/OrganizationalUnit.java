package com.example.evento.persistance.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
@Table(name = "or_unit")
public class OrganizationalUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unit_id")
    private Long id;

    @NonNull
    @Column(name = "unit_name")
    private String name;

    @Column(name = "unit_description")
    private String description;

    @Column(name = "or_unit_id")
    private Long organizationalUnit;

    public OrganizationalUnit() { }

    public OrganizationalUnit(String name, Long organizationalUnit) {
        this.name = name;
        this.organizationalUnit = organizationalUnit;
    }

    public OrganizationalUnit(String name, String description, Long organizationalUnit) {
        this.name = name;
        this.description = description;
        this.organizationalUnit = organizationalUnit;
    }

    public OrganizationalUnit(String region, String description) {
        this.name = region;
        this.description = description;
    }
}
