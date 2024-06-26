package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands;

public record CreateShedCommand(String shedName,
                                String typeShed,
                                String specie,
                                Long userId) {
    public CreateShedCommand {
        if (shedName == null || shedName.isBlank()) {
            throw new IllegalArgumentException("Shed name cannot be null or empty");
        }
        if (typeShed == null || typeShed.isBlank()) {
            throw new IllegalArgumentException("Type of shed cannot be null or empty");
        }
        if (specie == null || specie.isBlank()) {
            throw new IllegalArgumentException("Specie cannot be null or empty");
        }
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID cannot be null or non-positive");
        }
    }
}
