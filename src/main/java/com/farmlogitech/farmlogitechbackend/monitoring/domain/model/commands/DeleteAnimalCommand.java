package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands;

public record DeleteAnimalCommand(long animalId) {
    public DeleteAnimalCommand {
        if (animalId <= 0) {
            throw new IllegalArgumentException("Animal ID must be greater than zero");
        }
    }
}
