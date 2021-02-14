package com.example.evento.services;

import com.example.evento.persistance.model.OrganizationalUnit;

import java.util.List;
import java.util.Optional;

public interface OrganizationalUnitService {

    Optional<OrganizationalUnit> findById(Long id);

    String getOrType(Long orTypeId);

    List<OrganizationalUnit> getRegions();

    List<OrganizationalUnit> getAllCounties();

    List<OrganizationalUnit> getCounties(String requestedRegions);

}
