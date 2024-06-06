package com.farmlogitech.farmlogitechbackend.tasks.domain.services;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates.Task;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface TaskQueryService {
    Optional<Task> handle(GetTaskByIdQuery query);
//    Lists because don't return an object
    List<Task> handle(GetAllTasksQuery query);
    List<Task> handle(GetAllTasksByCollaboratorIdAndFarmerIdQuery query);
    List<Task> handle(GetAllTasksByCollaboratorIdQuery query);
    List<Task> handle(GetAllTaksByFarmerIdQuery query);
}
