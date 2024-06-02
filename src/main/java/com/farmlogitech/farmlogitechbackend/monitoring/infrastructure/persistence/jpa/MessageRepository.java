package com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<message, Long> {
    List<message> findAllByCollaboratorId(Long collaboratorId);
    List<message> findAllByCollaboratorIdAndFarmerId(Long collaboratorId, Long farmerId);
}