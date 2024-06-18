package com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.commands.CreateTaskCommand;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources.CreateTaskResource;

public class CreateTaskCommandFromResourceAssembler {
    public  static CreateTaskCommand toCommandFromResource(CreateTaskResource resource) {
        return new CreateTaskCommand(
                resource.description(),
                resource.status(),
                resource.time(),
                resource.endDate(),
                resource.collaboratorId());
    }


}
