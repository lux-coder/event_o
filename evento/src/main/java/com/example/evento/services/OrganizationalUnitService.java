package com.example.evento.services;

import com.example.evento.persistance.model.OrganizationalUnit;
import com.example.evento.persistance.model.dto.OrganizationDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrganizationalUnitService {

    Optional<OrganizationalUnit> findById(Long id);

    String getOrType(Long orTypeId);

    List<OrganizationalUnit> getRegions();

    List<OrganizationalUnit> getAllCounties();

    List<OrganizationDTO> getCountiesFull();

    List<OrganizationalUnit> getCounties(String requestedRegions);

    void saveCounty(Map<String, String> requestCounty);

    void saveRegion(Map<String, String> requestRegion);
}
