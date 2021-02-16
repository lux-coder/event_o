package com.example.evento.persistance.repository;

import com.example.evento.persistance.model.City;
import com.example.evento.persistance.model.dto.CityDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
    //@Query(value = "SELECT NEW com.example.evento.persistance.model.dto.CityDTO(c.cityId, c.cityName, c.citySize) " +
    //        "from city as c inner join city_size as cs on c.city_id = cs.city_id", nativeQuery = true)
    List<Object[]> getAllCities();

    @Query(value = "select city_id from city where city_name = :name", nativeQuery = true)
    Long getCityId(@Param("name") String name);
}