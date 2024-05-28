package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.commands;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;

public record CreateUserCommand (int id, String email, String password, Profile profile){
    public CreateUserCommand {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password cannot be null or empty");
        }

    }
}
