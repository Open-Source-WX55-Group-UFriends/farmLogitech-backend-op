package com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates.Task;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources.TaskResource;

public class TaskResourceFromEntityAssembler {
    public static  TaskResource toResourceFromEntity(Task entity) {
        return new TaskResource(
                entity.getId(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getTimeTask(),
                entity.getEndDate(),
                entity.getCollaboratorId(),
                entity.getFarmerId());
    }
}
