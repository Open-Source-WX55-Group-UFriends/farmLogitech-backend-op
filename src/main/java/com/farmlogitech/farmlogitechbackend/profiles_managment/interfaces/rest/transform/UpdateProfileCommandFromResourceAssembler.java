package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.UpdateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource( Long profileId,UpdateProfileResource resource) {
        return new UpdateProfileCommand(profileId,
                resource.firstName(), resource.lastName(),
                resource.direction(), resource.phone(), resource.gender(), resource.birthDate(),
                resource.documentNumber(), resource.documentType(), resource.role());
    }
}
