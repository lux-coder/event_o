package com.example.evento.persistance.repository;

import com.example.evento.persistance.model.OrganizationalUnit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

public interface OrganizationalUnitRepository extends CrudRepository<OrganizationalUnit, Long> {



    @Query(value = "select unit_name from or_unit where unit_id = ( " +
            "select or_unit_id from or_unit where unit_id = :countyId)", nativeQuery = true)
    String getRegionName(@Param("countyId") Long countyId);

    @Query(value = "select * from or_unit inner join or_type ON or_unit.unit_id = or_type.unit_id where or_type.type_name = 'REGIJA'", nativeQuery = true)
    List<OrganizationalUnit> getRegions();

    @Query(value = "select * from or_unit inner join or_type ON or_unit.unit_id = or_type.unit_id where or_type.type_name = 'ŽUPANIJA'", nativeQuery = true)
    List<OrganizationalUnit> getAllCounties();

    @Query(value = "select * from or_unit inner join or_type ON or_unit.unit_id = or_type.unit_id where or_type.type_name = 'ŽUPANIJA' " +
            "and or_unit_id in " +
            "(select or_unit_id from or_unit " +
            "where unit_name in (:requestedRegions) )", nativeQuery = true)
    List<OrganizationalUnit> getCounties(@Param("requestedRegions") String[] requestedRegions);

}
