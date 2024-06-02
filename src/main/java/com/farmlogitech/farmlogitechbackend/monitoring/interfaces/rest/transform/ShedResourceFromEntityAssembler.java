package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.ShedResource;

public class ShedResourceFromEntityAssembler {
    public static ShedResource toResourceFromEntity(Shed entity) {
        return new ShedResource(entity.getId(),
                entity.getShedName(),
                entity.getTypeShed(),
                entity.getSpecie());
    }
}

