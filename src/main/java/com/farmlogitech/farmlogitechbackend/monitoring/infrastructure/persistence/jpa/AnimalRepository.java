package com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Optional<Animal> findById(Long id);
    List<Animal> findAllByFarmId(long farmId);
}
