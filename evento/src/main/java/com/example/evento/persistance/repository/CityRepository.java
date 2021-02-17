package com.example.evento.persistance.repository;

import com.example.evento.persistance.model.City;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {

    @Query(value = "select city.city_id, city.city_name, city.or_unit_id, city_size.size_value, city_size.active from city " +
            "inner join or_unit on city.or_unit_id = or_unit.unit_id " +
            "inner join city_size on city.city_id = city_size.city_id " +
            "where or_unit.unit_id in " +
            "(select unit_id from or_unit where unit_name in (:requestedCounties) )", nativeQuery = true)
    List<Object[]> getCities(@Param("requestedCounties") String[] requestedCounties);

    @Query(value = "select city.city_id, city.city_name, city.or_unit_id, city_size.size_value, city_size.active from city " +
            "inner join city_size on city.city_id = city_size.city_id", nativeQuery = true)
    List<Object[]> getAllCities();

    @Query(value = "select city.city_id, city_name, size_value from city inner join city_size on city.city_id = city_size.city_id " +
            "where size_value = :size", nativeQuery = true)
    List<Object[]> getCityBySize(String size);

    @Query(value = "select city.city_id, city.city_name, city_size.size_value from city " +
            "inner join or_unit on city.or_unit_id = or_unit.unit_id " +
            "inner join city_size on city.city_id = city_size.city_id " +
            "where or_unit.unit_id in " +
            "(select unit_id from or_unit where unit_name in (:requestedCounties)) and city_size.size_value = :size", nativeQuery = true)
    List<Object[]> getCitiesSorted(@Param("requestedCounties") String[] requestedCounties, @Param("size") String size);

    @Query(value = "select city_id from city where city_name = :name", nativeQuery = true)
    Long getCityId(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "update city set city_name = :name, or_unit_id = :unitId where city_id = :id", nativeQuery = true)
    void editCity(@Param("name") String name, @Param("unitId") Long unitId, @Param("id") Long id);

}