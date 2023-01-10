package com.example.coffee.repository;

import com.example.coffee.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface StreetRepository extends JpaRepository<Street,Long> {
    Set<Street> findAllByNameStartsWithIgnoreCase(String name);
}
