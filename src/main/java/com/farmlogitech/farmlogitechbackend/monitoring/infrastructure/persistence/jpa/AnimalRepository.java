package com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    Optional<Animal> findById(Long id);
}
