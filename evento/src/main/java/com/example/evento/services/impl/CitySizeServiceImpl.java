package com.example.evento.services.impl;

import com.example.evento.persistance.model.CitySize;
import com.example.evento.persistance.repository.CitySizeRepository;
import com.example.evento.services.CitySizeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CitySizeServiceImpl implements CitySizeService {

    @Autowired
    CitySizeRepository citySizeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(CitySizeServiceImpl.class);

    @Override
    public Optional<CitySize> findById(Long id) {
        LOGGER.info("In CitySizeService, findById");
        return citySizeRepository.findById(id);
    }

    @Override
    public Optional<CitySize> findByCityId(Long id) {
        LOGGER.info("In CitySizeService, findByCityId");
        return citySizeRepository.findByCityId(id);
    }
}
