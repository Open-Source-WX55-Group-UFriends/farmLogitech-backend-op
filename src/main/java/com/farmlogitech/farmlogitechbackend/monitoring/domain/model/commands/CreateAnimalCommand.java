package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands;

public record CreateAnimalCommand(String shed,
                                  Integer age,
                                  String location,
                                  String healthCondition,
                                  Long userId) {
    public CreateAnimalCommand {
        if (shed == null || shed.isBlank()) {
            throw new IllegalArgumentException("Shed cannot be null or empty");
        }
        if (age == null || age < 0) {
            throw new IllegalArgumentException("Age cannot be null or negative");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        if (healthCondition == null || healthCondition.isBlank()) {
            throw new IllegalArgumentException("Health condition cannot be null or empty");
        }
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID cannot be null or non-positive");
        }

    }
}

