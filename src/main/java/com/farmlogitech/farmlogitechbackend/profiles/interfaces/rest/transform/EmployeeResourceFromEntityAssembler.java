package com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Employee;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.EmployeeResource;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.ProfileResource;

public class EmployeeResourceFromEntityAssembler {

    public static EmployeeResource toResourceFromEntity(Employee entity) {
        return new EmployeeResource(
                entity.getId(),
                entity.getName(),
                entity.getPhone(),
               entity.getUsername(),
               entity.getPassword(),
                entity.getPosition(),
                entity.getFarmId(),
                entity.getFarmerId()

        );

    }
}
