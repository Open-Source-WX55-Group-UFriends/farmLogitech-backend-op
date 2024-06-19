package com.farmlogitech.farmlogitechbackend.tasks.infrastructure.persistance.jpa.repositories;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllTaskByCollaboratorIdAndFarmerId(Long collaboratorId, Long farmerId);
    List<Task> findAllTaskByCollaboratorId(Long collaboratorId);
    List<Task> findAllTaskByFarmerId(Long farmerId);


}
