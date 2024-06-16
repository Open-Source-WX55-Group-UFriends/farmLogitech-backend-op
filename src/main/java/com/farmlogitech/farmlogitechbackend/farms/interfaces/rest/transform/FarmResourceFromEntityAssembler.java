package com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.FarmResource;

public class FarmResourceFromEntityAssembler {
    public static FarmResource toResourceFromEntity(Farm entity) {

        return new FarmResource(entity.getId(),entity.getFarmName(),
                entity.getLocation(),
                entity.getType(),
                entity.getInfrastructure(),
                entity.getServices(),
                entity.getStatus(),
                entity.getCertificates(), entity.getImage(), entity.getProfileId(), entity.getPrice(), entity.getSurface(), entity.getProduct(), entity.getHighlights());

    }

}

