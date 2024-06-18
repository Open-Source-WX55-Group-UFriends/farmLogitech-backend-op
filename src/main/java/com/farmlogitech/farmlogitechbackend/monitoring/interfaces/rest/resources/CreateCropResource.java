package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources;

public record CreateCropResource(String shed,
                                 String typeCrop,
                                 String seedtime) {
    public CreateCropResource {
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

