package com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands;

public record CreateProfileCommand(String firstName, String lastName, String email, String direction, String documentNumber, String documentType, Long userId) { }
