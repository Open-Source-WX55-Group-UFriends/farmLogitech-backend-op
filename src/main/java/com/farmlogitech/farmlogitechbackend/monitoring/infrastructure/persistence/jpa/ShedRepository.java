package com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ShedRepository extends JpaRepository<Shed, Integer> {
    Optional<Shed> findById(Long id);
    List<Shed> findAllByFarmId(long farmId);

    Optional<Shed> findByShedName(String s);
}
