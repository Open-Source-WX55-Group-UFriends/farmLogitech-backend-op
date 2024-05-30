package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateUserCommand;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(resource.firstName(),
                resource.lastName(), resource.direction(), resource.phone(), resource.gender(), resource.birthDate(), resource.documentNumber(), resource.documentType(), resource.role(),
                resource.email(), resource.password());
    }
}
