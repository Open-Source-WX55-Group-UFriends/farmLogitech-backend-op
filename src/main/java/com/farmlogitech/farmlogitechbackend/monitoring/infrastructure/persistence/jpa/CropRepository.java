package com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropRepository extends JpaRepository<Crop, Integer> {
    Optional<Crop> findById(Long id);
}
