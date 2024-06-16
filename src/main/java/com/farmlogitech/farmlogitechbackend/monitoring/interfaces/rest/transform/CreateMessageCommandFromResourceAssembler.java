package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateAnimalCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateMessageCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateAnimalResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateMessageResource;

public class CreateMessageCommandFromResourceAssembler {
    public static CreateMessageCommand toCommandFromResource(CreateMessageResource resource) {
        return new CreateMessageCommand(resource.description(), resource.collaboratorId(), resource.farmerId(), resource.transmitterId());
    }
}
