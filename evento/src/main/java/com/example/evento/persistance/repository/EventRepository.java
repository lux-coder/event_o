package com.example.evento.persistance.repository;

import com.example.evento.persistance.model.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends CrudRepository<Event, Long> {

    Optional<Event> findByName(String name);

    @Query(value = "select * from events " +
            "inner join city_size on events.city_id = city_size.city_id " +
            "where city_size.active = true", nativeQuery = true)
    List<Event> findAllInActiveCity();

    @Query(value = "select event_name from events " +
            "where event_name like %:name%", nativeQuery = true)
    List<String> searchByName(@Param("name") String name);

    @Query(value = "select * from events " +
            "inner join city on events.city_id = city.city_id " +
            "where city.city_name in (:city) ", nativeQuery = true)
    List<Event> findByCity(@Param("city") String[] city);

    @Query(value = "select * from events " +
            "inner join city on events.city_id = city.city_id " +
            "inner join or_unit on city.or_unit_id = or_unit.unit_id " +
            "where or_unit.or_unit_id = any ( " +
            "select or_unit.or_unit_id from or_unit " +
            "where or_unit.unit_name in (:region))", nativeQuery = true)
    List<Event> findByRegion(@Param("region") String[] region);

    @Query(value = "select * from events " +
            "inner join city on events.city_id = city.city_id " +
            "inner join or_unit on city.or_unit_id = or_unit.unit_id " +
            "where or_unit.unit_id = any ( " +
            "select or_unit.unit_id from or_unit " +
            "where or_unit.unit_name in (:county))", nativeQuery = true)
    List<Event> findByCounty(@Param("county") String[] county);

    @Query(value = "select * from events " +
            "where events.events_name like %:name% " +
            "and events.event_start like %:eventStart% and events.event_end like %:eventEnd% " +
            "and events.entrance = :entrance", nativeQuery = true)
    List<Event> additionalFiltering(@Param("name") String name, @Param("eventStart") String eventStart,
                                    @Param("eventEnd") String eventEnd, @Param("entrance") Boolean entrance);

    @Query(value = "select * from events where events.event_name like %:eventName%", nativeQuery = true)
    List<Event> listByName(@Param("eventName") String eventName);

    @Query(value = "select * from events where events.event_start = cast(:date AS timestamp)", nativeQuery = true)
    List<Event> getByStartDate(@Param("date") String eventStartDate);

    @Query(value = "select * from events where events.event_end = cast(:date AS timestamp)", nativeQuery = true)
    List<Event> getByEndDate(@Param("date") String eventEndDate);

    @Query(value = "select * from events where events.event_start between cast(:dateStart as timestamp) " +
            "and cast(:dateEnd as timestamp)", nativeQuery = true)
    List<Event> getByDateBetween(@Param("dateStart") String eventStartDate, @Param("dateEnd") String eventEndDate);

    @Query(value = "select * from events where events.entrance = :entrance", nativeQuery = true)
    List<Event> getByEntrance(@Param("entrance") Boolean entrance);

    @Transactional
    @Modifying
    @Query(value = "insert into events(event_name, event_start, event_end, entrance, city_id) values (:event, " +
            "cast(:start as timestamp), cast(:end as timestamp), :entrance, :city)", nativeQuery = true)
    void saveEvent(@Param("event") String event, @Param("start") String start, @Param("end") String end, @Param("entrance")
            Boolean entrance, @Param("city") Long city);


    //List<Event> findByDateCreatedBetween(Timestamp start, Timestamp end);
}
