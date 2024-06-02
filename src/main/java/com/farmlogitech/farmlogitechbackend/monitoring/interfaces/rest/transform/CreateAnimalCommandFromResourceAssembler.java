package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateAnimalCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateAnimalResource;

public class CreateAnimalCommandFromResourceAssembler {
    public static CreateAnimalCommand toCommandFromResource(CreateAnimalResource resource) {
        return new CreateAnimalCommand(resource.shed(),
                resource.age(),
                resource.location(),
                resource.healthCondition());
    }
}

