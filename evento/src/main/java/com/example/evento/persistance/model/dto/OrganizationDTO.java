package com.example.evento.persistance.model.dto;

import lombok.Data;

@Data
public class OrganizationDTO {

    private Long id;
    private String name;
    private String description;
    private String organizationalUnit;
}
