package com.example.evento.persistance.repository;

import com.example.evento.persistance.model.CitySize;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CitySizeRepository extends CrudRepository<CitySize, Long> {

    @Query(value = "select * from city_size where city_id = :cityId", nativeQuery = true)
    Optional<CitySize> findByCityId(@Param("cityId")Long cityId);
}
