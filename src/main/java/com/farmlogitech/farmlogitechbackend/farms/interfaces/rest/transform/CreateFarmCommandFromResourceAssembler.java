package com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.CreateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.CreateFarmResource;

public class CreateFarmCommandFromResourceAssembler {
    public static CreateFarmCommand toCommandFromResource(CreateFarmResource resource) {
        return new CreateFarmCommand( resource.farmName(),
                resource.location(),
                resource.type(),
                resource.infrastructure(),
                resource.services(),
                resource.status(),
                resource.certificates(), resource.image(), resource.profileId(), resource.price(), resource.Surface(), resource.product(), resource.highlights());


    }
}
