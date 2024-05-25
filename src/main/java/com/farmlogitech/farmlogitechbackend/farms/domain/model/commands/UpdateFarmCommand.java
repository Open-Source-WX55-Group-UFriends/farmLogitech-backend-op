package com.farmlogitech.farmlogitechbackend.farms.domain.model.commands;

public record UpdateFarmCommand (int id,String farmName,
                                String location,
                                String type,
                                String infrastructure,
                                String services,
                                String status,
                                String certificates) {
    public UpdateFarmCommand {
        if (farmName == null || farmName.isBlank()) {
            throw new IllegalArgumentException("Farm name cannot be null or empty");
        }
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("type cannot be null or empty");
        }

        if (infrastructure == null || infrastructure.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        if (services == null || services.isBlank()) {
            throw new IllegalArgumentException("services cannot be null or empty");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }

        if (certificates == null || certificates.isBlank()) {
            throw new IllegalArgumentException("certificates cannot be null or empty");
        }

    }
}