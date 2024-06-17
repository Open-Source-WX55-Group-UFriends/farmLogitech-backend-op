package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.AnimalResource;

public class AnimalResourceFromEntityAssembler {
    public static AnimalResource toResourceFromEntity(Animal entity) {
        return new AnimalResource(entity.getId(),
                entity.getShed(),
                entity.getAge(),
                entity.getLocation(),
                entity.getHealthCondition(),
                entity.getUserId(),
                entity.getFarmId());
    }
}

