package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateShedCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateShedResource;

public class CreateShedCommandFromResourceAssembler {
    public static CreateShedCommand toCommandFromResource(CreateShedResource resource) {
        return new CreateShedCommand(resource.shedName(),
                resource.typeShed(),
                resource.specie());
    }
}

