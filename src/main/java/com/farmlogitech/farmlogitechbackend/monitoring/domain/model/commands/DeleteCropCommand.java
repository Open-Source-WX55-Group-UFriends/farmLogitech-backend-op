package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands;

public record DeleteCropCommand(long cropId) {
    public DeleteCropCommand {
        if (cropId <= 0) {
            throw new IllegalArgumentException("Crop ID must be greater than zero");
        }
    }
}
