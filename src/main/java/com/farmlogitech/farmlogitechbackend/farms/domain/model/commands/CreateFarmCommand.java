package com.farmlogitech.farmlogitechbackend.farms.domain.model.commands;

public record CreateFarmCommand(String farmName, String location) {
    public CreateFarmCommand {
        if (farmName == null || farmName.isBlank()) {
            throw new IllegalArgumentException("Farm name cannot be null or empty");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
    }
}
