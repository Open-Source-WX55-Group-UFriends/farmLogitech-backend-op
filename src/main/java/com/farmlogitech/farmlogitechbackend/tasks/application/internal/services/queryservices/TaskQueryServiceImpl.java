package com.farmlogitech.farmlogitechbackend.tasks.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates.Task;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.queries.*;
import com.farmlogitech.farmlogitechbackend.tasks.domain.services.TaskQueryService;
import com.farmlogitech.farmlogitechbackend.tasks.infrastructure.persistance.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskQueryServiceImpl implements TaskQueryService {
    private final TaskRepository taskRepository;

    public TaskQueryServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query) {
        return taskRepository.findById(query.id());

    }

    @Override
    public List<Task> handle(GetAllTasksQuery query) {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> handle(GetAllTasksByCollaboratorIdAndFarmerIdQuery query) {
        return taskRepository.findAllTaskByCollaboratorIdAndFarmerId(query.collaboratorId(), query.farmerId());
    }

    @Override
    public List<Task> handle(GetAllTasksByCollaboratorIdQuery query) {
        return taskRepository.findAllTaskByCollaboratorId(query.collaboratorId());
    }

    @Override
    public List<Task> handle(GetAllTaksByFarmerIdQuery query) {
        return taskRepository.findAllTaskByFarmerId(query.farmerId());
    }
}
