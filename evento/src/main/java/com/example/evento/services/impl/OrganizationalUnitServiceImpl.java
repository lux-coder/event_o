package com.example.evento.services.impl;

import com.example.evento.persistance.model.OrganizationalUnit;
import com.example.evento.persistance.repository.OrganizationalUnitRepository;
import com.example.evento.services.OrganizationalUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationalUnitServiceImpl implements OrganizationalUnitService {

    @Autowired
    private OrganizationalUnitRepository organizationalUnitRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationalUnitServiceImpl.class);

    @Override
    public Optional<OrganizationalUnit> findById(Long id) {
        return organizationalUnitRepository.findById(id);
    }

    @Override
    public String getOrType(Long orTypeId) {
        return organizationalUnitRepository.getRegionName(orTypeId);
    }

    @Override
    public List<OrganizationalUnit> getRegions() {
        return organizationalUnitRepository.getRegions();
    }

    @Override
    public List<OrganizationalUnit> getAllCounties() {
        return organizationalUnitRepository.getAllCounties();
    }

    @Override
    public List<OrganizationalUnit> getCounties(String requestedRegions) {
        LOGGER.info("In OrganizationalUnitService, getCounties with requestedRegions:{}", requestedRegions);

        String regionsReq = requestedRegions.substring(1, requestedRegions.length()-1).replaceAll("\"", "");
        List<String> regionsList = new ArrayList<>(Arrays.asList(regionsReq.split(",")));
        String[] regions = regionsList.toArray(new String[0]);

        return organizationalUnitRepository.getCounties(regions);
    }
}
