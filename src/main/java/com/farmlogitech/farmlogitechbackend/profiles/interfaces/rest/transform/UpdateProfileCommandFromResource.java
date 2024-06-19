package com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.UpdateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.UpdateFarmResource;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.UpdateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResource {
    public static UpdateProfileCommand toCommandFromResource(UpdateProfileResource resource) {
        return new UpdateProfileCommand( resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.direction(),
                resource.documentNumber(),
                resource.documentType());


    }
}
