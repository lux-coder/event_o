package com.example.evento.persistance.repository;

import com.example.evento.persistance.model.OrganizationalUnit;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrganizationalUnitRepository extends CrudRepository<OrganizationalUnit, Long> {

    @Query(value = "select unit_name from or_unit where unit_id = ( " +
            "select or_unit_id from or_unit where unit_id = :countyId)", nativeQuery = true)
    String getRegionName(@Param("countyId") Long countyId);

    @Query(value = "select unit_name from or_unit where unit_id = :countyId", nativeQuery = true)
    String getCountyName(@Param("countyId") Long countyId);

    @Query(value = "select * from or_unit inner join or_type ON or_unit.unit_id = or_type.unit_id where or_type.type_name = 'REGIJA'", nativeQuery = true)
    List<OrganizationalUnit> getRegions();

    @Query(value = "select * from or_unit inner join or_type ON or_unit.unit_id = or_type.unit_id where or_type.type_name = 'ŽUPANIJA'", nativeQuery = true)
    List<OrganizationalUnit> getAllCounties();

    @Query(value = "select * from or_unit inner join or_type ON or_unit.unit_id = or_type.unit_id where or_type.type_name = 'ŽUPANIJA' " +
            "and or_unit_id in " +
            "(select or_unit_id from or_unit " +
            "where unit_name in (:requestedRegions) )", nativeQuery = true)
    List<OrganizationalUnit> getCounties(@Param("requestedRegions") String[] requestedRegions);

    @Query(value = "select unit_id from or_unit where unit_name = :county", nativeQuery = true)
    Long getCountyId(@Param("county") String county);

    @Query(value = "select or_unit_id from or_unit where unit_name = :region", nativeQuery = true)
    Long getRegionId(@Param("region") String region);

    @Transactional
    @Modifying
    @Query(value = "insert into or_unit(unit_name, unit_description, or_unit_id) values (:name, :description, " +
            "(select last_value from or_unit_unit_id_seq))", nativeQuery = true)
    void saveRegion(@Param("name") String name, @Param("description") String description);

    @Query(value = "select last_value from or_unit_unit_id_seq", nativeQuery = true)
    Long getLastValue();

    @Transactional
    @Modifying
    @Query(value = "update or_unit set unit_name = :name, unit_description = :description where unit_id = :id", nativeQuery = true)
    void editRegion(@Param("name") String name, @Param("description") String description, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update or_unit set unit_name = :name, unit_description = :description, or_unit_id = :unitId where unit_id = :id", nativeQuery = true)
    void editCounty(@Param("name") String name, @Param("description") String description, @Param("unitId") Long unitId, @Param("id") Long id);
}
