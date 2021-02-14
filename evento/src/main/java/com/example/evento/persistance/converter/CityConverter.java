package com.example.evento.persistance.converter;

import com.example.evento.persistance.model.dto.CityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityConverter.class);

    public List<CityDTO> convertCity(List<Object[]> cities) {
        LOGGER.info("In CityConverter, convertCity");

        List<CityDTO> cityDTOS = cities.stream()
                .map(city -> new CityDTO((Integer)city[0], (String)city[1], (Integer)city[2], (String)city[3], (Boolean)city[4]))
                .collect(Collectors.toList());

        return cityDTOS;
    }

}
