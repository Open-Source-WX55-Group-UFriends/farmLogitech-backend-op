package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands;

public record CreateCropCommand(String shed,
                                String typeCrop,
                                String seedtime,
                                Long userId,
                                Long farmId) {
    public CreateCropCommand {
        if (shed == null || shed.isBlank()) {
            throw new IllegalArgumentException("Shed cannot be null or empty");
        }
        if (typeCrop == null || typeCrop.isBlank()) {
            throw new IllegalArgumentException("Type of crop cannot be null or empty");
        }
        if (seedtime == null || seedtime.isBlank()) {
            throw new IllegalArgumentException("Seed time cannot be null or empty");
        }
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID cannot be null or non-positive");
        }
        if (farmId == null || farmId <= 0) {
            throw new IllegalArgumentException("Farm ID cannot be null or non-positive");
        }
    }
}

