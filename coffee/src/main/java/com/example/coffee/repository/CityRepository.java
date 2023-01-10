package com.example.coffee.repository;

import com.example.coffee.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CityRepository extends JpaRepository<City,Long> {
    Set<City> findAllByNameStartsWithIgnoreCase(String name);
}
