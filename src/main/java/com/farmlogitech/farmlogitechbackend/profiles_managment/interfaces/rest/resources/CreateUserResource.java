package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.valueobjects.ProfileId;

public record CreateUserResource( String firstName, String lastName, String direction, String phone, String gender, String birthDate, String documentNumber, String documentType, String role, String email, String password) {
    public CreateUserResource {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }

    }

}
