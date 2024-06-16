package com.farmlogitech.farmlogitechbackend.monitoring.domain.services;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateAnimalCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.DeleteAnimalCommand;

import java.util.Optional;

public interface AnimalCommandService {
    Optional<Animal> handle(CreateAnimalCommand command);

    Optional<Animal> handle(DeleteAnimalCommand command);
}
