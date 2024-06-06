package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateAnimalCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateAnimalResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateMessageResource;

public class CreateMessageCommandFromResourceAssembler {
    public static CreateMessageResource toCommandFromResource(CreateMessageResource resource) {
        return new CreateMessageResource(resource.description(), resource.collaboratorId(), resource.farmerId());
    }
}
