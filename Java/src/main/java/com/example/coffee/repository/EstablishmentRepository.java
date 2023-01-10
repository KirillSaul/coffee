package com.example.coffee.repository;

import com.example.coffee.entity.Establishment;
import com.example.coffee.protocol.establishment.EstablishmentEditDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstablishmentRepository extends JpaRepository <Establishment, Long> {
    @EntityGraph(value = Establishment.GRAPH_CITY_STREET)
    Page<Establishment> findAllByActiveTrue(Pageable pageable);
    @EntityGraph(value = Establishment.GRAPH_CITY_STREET)
    Page<Establishment> findAllByActiveFalse(Pageable pageable);
    @EntityGraph(value = Establishment.GRAPH_CITY_STREET)
    Page<Establishment> findAllByCityIdCityInAndActiveTrue(Iterable<Long> idCities, Pageable pageable);
    @EntityGraph(value = Establishment.GRAPH_CITY_STREET)
    Page<Establishment> findAllByCityIdCityInAndActiveFalse(Iterable<Long> idCities, Pageable pageable);
    @EntityGraph(value = Establishment.GRAPH_ALL)
    Optional<Establishment> findByIdEstablishment(Long idEstablishment);
}
