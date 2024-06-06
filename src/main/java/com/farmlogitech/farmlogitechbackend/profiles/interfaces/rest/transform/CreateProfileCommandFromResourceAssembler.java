package com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.transform;


import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.firstName(),  resource.lastName(),  resource.email(), resource.direction(), resource.documentNumber(),  resource.documentType() );
    }
}
