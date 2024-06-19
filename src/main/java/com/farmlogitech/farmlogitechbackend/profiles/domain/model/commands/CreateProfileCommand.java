package com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands;

import java.util.Objects;

public record CreateProfileCommand(String firstName, String lastName, String email, String direction, String documentNumber, String documentType) {


    public CreateProfileCommand {
        Objects.requireNonNull(firstName, "firstName cannot be null");
        Objects.requireNonNull(lastName, "lastName cannot be null");
        Objects.requireNonNull(email, "email cannot be null");
        Objects.requireNonNull(direction, "direction cannot be null");
        Objects.requireNonNull(documentNumber, "documentNumber cannot be null");
        Objects.requireNonNull(documentType, "documentType cannot be null");
    }














}
