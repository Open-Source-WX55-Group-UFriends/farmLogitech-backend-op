package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources;

public record CreateAnimalResource(String shed,
                                   Integer age,
                                   String location,
                                   String healthCondition) {
    public CreateAnimalResource {
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
    }
}

