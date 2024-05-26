package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.transform;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateProfileCommnad;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommnad toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommnad(resource.id(), resource.firstName(), resource.lastName(),
                resource.direction(), resource.phone(), resource.gender(), resource.birthDate(),
                resource.documentNumber(), resource.documentType(), resource.role());
    }

}
