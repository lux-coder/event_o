package com.example.evento.services.impl;

import com.example.evento.persistance.model.OrganizationalType;
import com.example.evento.persistance.model.OrganizationalUnit;
import com.example.evento.persistance.model.dto.OrganizationDTO;
import com.example.evento.persistance.repository.OrganizationalTypeRepository;
import com.example.evento.persistance.repository.OrganizationalUnitRepository;
import com.example.evento.services.OrganizationalUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class OrganizationalUnitServiceImpl implements OrganizationalUnitService {

    @Autowired
    private OrganizationalUnitRepository organizationalUnitRepository;

    @Autowired
    private OrganizationalTypeRepository organizationalTypeRepository;

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
    public List<OrganizationDTO> getCountiesFull() {

        List<OrganizationDTO> organizationDTOS = new ArrayList<>();
        List<OrganizationalUnit> units = organizationalUnitRepository.getAllCounties();

        for (OrganizationalUnit u: units) {
            OrganizationDTO organizationDTO = new OrganizationDTO();
            organizationDTO.setId(u.getId());
            organizationDTO.setName(u.getName());
            organizationDTO.setDescription(u.getDescription());
            organizationDTO.setOrganizationalUnit(organizationalUnitRepository.getRegionName(u.getOrganizationalUnit()));
            organizationDTOS.add(organizationDTO);
        }
        return organizationDTOS;
    }

    @Override
    public List<OrganizationalUnit> getCounties(String requestedRegions) {
        LOGGER.info("In OrganizationalUnitService, getCounties with requestedRegions:{}", requestedRegions);

        String regionsReq = requestedRegions.substring(1, requestedRegions.length()-1).replaceAll("\"", "");
        List<String> regionsList = new ArrayList<>(Arrays.asList(regionsReq.split(",")));
        String[] regions = regionsList.toArray(new String[0]);

        return organizationalUnitRepository.getCounties(regions);
    }

    @Override
    public void saveCounty(Map<String, String> requestCounty) {
        LOGGER.info("In OrganizationalUnitService, saveCounty with requestCounty:{}", requestCounty);

        OrganizationalUnit organizationalUnit = new OrganizationalUnit(requestCounty.get("county"), requestCounty.get("description"),
                organizationalUnitRepository.getCountyId(requestCounty.get("regions$")));
        organizationalUnit = organizationalUnitRepository.save(organizationalUnit);

        organizationalTypeRepository.save(new OrganizationalType(requestCounty.get("regionType"), organizationalUnit.getId(), true));

        LOGGER.info("COUNTY SAVED!");
    }

    @Override
    public void saveRegion(Map<String, String> requestRegion) {
        LOGGER.info("In OrganizationalUnitService, saveRegion with requestCounty:{}", requestRegion);

        OrganizationalUnit organizationalUnit = new OrganizationalUnit(requestRegion.get("region"), requestRegion.get("description"));
        organizationalUnitRepository.saveRegion(organizationalUnit.getName(), organizationalUnit.getDescription());

        organizationalTypeRepository.save(new OrganizationalType(requestRegion.get("regionType"), organizationalUnitRepository.getLastValue(), true));

        LOGGER.info("REGION SAVED!");
    }

    @Override
    public void deleteCounty(Map<String, String> requestedCounty) {
        LOGGER.info("In OrganizationalUnitService, deleteCounty: {}", requestedCounty);
        organizationalUnitRepository.deleteById(Long.valueOf(requestedCounty.get("id")));
    }

    @Override
    public void editRegion(Map<String, String> requestedRegion) {
        LOGGER.info("In OrganizationalUnitService, editRegion: {}", requestedRegion);
        organizationalUnitRepository.editRegion(requestedRegion.get("name"), requestedRegion.get("description"),
                Long.valueOf(requestedRegion.get("id")));
    }

    @Override
    public void editCounty(Map<String, String> requestedRegion) {
        LOGGER.info("In OrganizationalUnitService, editCounty: {}", requestedRegion);
        organizationalUnitRepository.editCounty(requestedRegion.get("name"), requestedRegion.get("description"),
                organizationalUnitRepository.getRegionId(requestedRegion.get("organizationalUnit")),
                Long.valueOf(requestedRegion.get("id")));
    }
}
