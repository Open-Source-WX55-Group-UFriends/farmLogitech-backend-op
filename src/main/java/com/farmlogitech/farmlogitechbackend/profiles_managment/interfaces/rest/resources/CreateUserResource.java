package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;

public record CreateUserResource(int id, String email, String password, Profile profile) {
    public CreateUserResource {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }
        if (profile==null) {
            throw new IllegalArgumentException("profileId cannot be null or empty");
        }
    }

}
