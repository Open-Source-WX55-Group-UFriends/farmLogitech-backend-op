package com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.transform;


import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(), entity.getFullName(), entity.getEmail(), entity.getDirection(), entity.getDocumentNumber(), entity.getDocumentType());

    }


}
