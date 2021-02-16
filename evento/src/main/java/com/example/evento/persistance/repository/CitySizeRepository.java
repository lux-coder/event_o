package com.example.evento.persistance.repository;

import com.example.evento.persistance.model.CitySize;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CitySizeRepository extends CrudRepository<CitySize, Long> {

    @Query(value = "select * from city_size where city_id = :cityId", nativeQuery = true)
    Optional<CitySize> findByCityId(@Param("cityId")Long cityId);

    @Query(value = "delete from city_size where city_id = :city", nativeQuery = true)
    void deleteByCityId(@Param("city") Long cityId);

    @Transactional
    @Modifying
    @Query(value = "update city_size set size_value = :size, active = :active where city_id = :id", nativeQuery = true)
    void editCitySize(@Param("size") String size, @Param("active") Boolean active, @Param("id") Long id);
}
