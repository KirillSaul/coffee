package com.example.coffee.repository;

import com.example.coffee.entity.PrivatePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivatePolicyRepository extends JpaRepository<PrivatePolicy, Integer> {
    Optional<PrivatePolicy> findByIdPrivatePolicyNotNull();
}
