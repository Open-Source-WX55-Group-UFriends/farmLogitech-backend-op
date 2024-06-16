package com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByCollaboratorId(Long collaboratorId);
    List<Message> findAllByCollaboratorIdAndFarmerId(Long collaboratorId, Long farmerId);
    List<Message> findAllByCollaboratorIdAndTransmitterIdNot(Long collaboratorId, Long transmitterId);

}