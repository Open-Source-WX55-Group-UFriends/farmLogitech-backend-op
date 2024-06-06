package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands;

public record CreateCropCommand(String shed,
                                String typeCrop,
                                String seedtime) {
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
    }
}

