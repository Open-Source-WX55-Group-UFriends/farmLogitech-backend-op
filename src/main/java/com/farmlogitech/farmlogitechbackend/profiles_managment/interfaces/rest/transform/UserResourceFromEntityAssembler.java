package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity) {
         return new UserResource(
                entity.getEmail(),
                entity.getPassword(),
                entity.getProfileId(),
                 entity.getSubscriptionId()
             );
    }
}
