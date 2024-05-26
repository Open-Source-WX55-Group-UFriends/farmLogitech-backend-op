package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.transform;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource toResourceFromEntity(Profile entity) {
        return new ProfileResource(entity.getId(), entity.getFirstName(), entity.getLastName(),
                entity.getDirection(), entity.getPhone(), entity.getGender(), entity.getBirthDate(),
                entity.getDocumentNumber(), entity.getDocumentType(), entity.getRole());

    }

}
