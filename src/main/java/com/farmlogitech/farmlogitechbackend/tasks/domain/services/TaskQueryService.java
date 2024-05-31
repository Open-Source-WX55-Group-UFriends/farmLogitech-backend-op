package com.farmlogitech.farmlogitechbackend.tasks.domain.services;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates.Task;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.queries.GetAllTasksQuery;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.queries.GetTaskByIdQuery;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.queries.GetTasksByStatusQuery;

import java.util.List;
import java.util.Optional;

public interface TaskQueryService {
    Optional<Task> handle(GetTaskByIdQuery query);
//    Lists because don't return an object
    List<Task> handle(GetAllTasksQuery query);
    List<Task> handle(GetTasksByStatusQuery query);
}
