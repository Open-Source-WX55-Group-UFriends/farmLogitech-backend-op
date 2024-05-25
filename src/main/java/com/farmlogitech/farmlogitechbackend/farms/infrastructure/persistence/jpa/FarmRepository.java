package com.farmlogitech.farmlogitechbackend.farms.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Integer>{
    List<Farm> findAllByLocation(String location);
    Optional<Farm> findById(Integer id);
}

