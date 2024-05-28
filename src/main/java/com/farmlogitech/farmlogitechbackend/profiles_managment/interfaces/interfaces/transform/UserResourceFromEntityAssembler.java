package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.transform;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.interfaces.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(entity.getId(), entity.getEmail(), entity.getPassword(), entity.getProfile());
    }
}
