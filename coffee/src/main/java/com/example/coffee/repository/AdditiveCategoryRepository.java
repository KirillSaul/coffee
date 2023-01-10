package com.example.coffee.repository;

import com.example.coffee.entity.AdditiveCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditiveCategoryRepository extends JpaRepository<AdditiveCategory, Long> {
}