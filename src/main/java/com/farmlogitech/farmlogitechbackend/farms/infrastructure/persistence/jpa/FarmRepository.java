package com.farmlogitech.farmlogitechbackend.farms.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Integer>{
    List<Farm> findAllByLocation(String location);
    Optional<Farm> findById(Integer id);
    @Modifying
    @Transactional
    @Query("UPDATE Farm f SET f.farmName = :farmName, f.location = :location, f.type = :type, f.infrastructure = :infrastructure, f.services = :services, f.status = :status, f.certificates = :certificates, f.image=:image WHERE f.id = :id")
    Optional<Farm> updateById(Integer id);

}

