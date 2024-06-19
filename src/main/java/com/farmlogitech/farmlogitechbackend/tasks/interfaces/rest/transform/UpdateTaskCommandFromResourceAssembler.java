package com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.UpdateTaskStatusCommand;

public class UpdateTaskCommandFromResourceAssembler {
    public static UpdateTaskStatusCommand toCommandFromResource(Long taskId) {
        return new UpdateTaskStatusCommand(taskId);
    }
}
