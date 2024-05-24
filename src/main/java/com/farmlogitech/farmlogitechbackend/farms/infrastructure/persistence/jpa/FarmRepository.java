package com.farmlogitech.farmlogitechbackend.farms.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmRepository extends JpaRepository<Farm, String>{

}

