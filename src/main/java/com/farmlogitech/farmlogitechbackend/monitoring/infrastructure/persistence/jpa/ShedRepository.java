package com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShedRepository extends JpaRepository<Shed, Integer> {
    Optional<Shed> findById(Long id);
}
