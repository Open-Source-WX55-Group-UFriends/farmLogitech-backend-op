package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.services;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateAnimalCommand;

import java.util.Optional;

public interface AnimalCommandService {
    Optional<Animal> handle(CreateAnimalCommand command);
}
