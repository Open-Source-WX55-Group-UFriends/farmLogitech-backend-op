package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.transform;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands.CreateUserCommand;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(resource.id(), resource.email(), resource.password(), resource.profile());
    }
}
