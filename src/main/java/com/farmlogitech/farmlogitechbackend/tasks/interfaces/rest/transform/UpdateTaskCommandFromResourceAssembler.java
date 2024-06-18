package com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.UpdateTaskStatusCommand;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources.UpdateTaskResource;

public class UpdateTaskCommandFromResourceAssembler {
    public static UpdateTaskStatusCommand toCommandFromResource(Long taskId, UpdateTaskResource resource) {
        return new UpdateTaskStatusCommand(taskId);
    }
}
