package com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.CreateFarmResource;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateEmployeeCommand;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.CreateEmployeeResource;

public class CreateEmployeeCommandFromResourceAssembler {
    public static CreateEmployeeCommand toCommandFromResource(CreateEmployeeResource resource) {
        return new CreateEmployeeCommand(
                  resource.name(),
                    resource.phone(),
                    resource.username(),
                    resource.password(),
                    resource.position(),
                    resource.farmId()

        );
    }
}
