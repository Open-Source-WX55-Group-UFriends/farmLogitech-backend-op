package com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.CreateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.UpdateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.CreateFarmResource;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.UpdateFarmResource;

public class UpdateFarmCommandFromResourceAssembler {
    public static UpdateFarmCommand toCommandFromResource(UpdateFarmResource resource) {
        return new UpdateFarmCommand(resource.id(), resource.farmName(),
                resource.location(),
                resource.type(),
                resource.infrastructure(),
                resource.services(),
                resource.status(),
                resource.certificates(), resource.image());


    }
}
