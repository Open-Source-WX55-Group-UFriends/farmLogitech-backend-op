package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateAnimalCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.AnimalCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalCommandServiceImpl implements AnimalCommandService {
    private AnimalRepository animalRepository;

    public AnimalCommandServiceImpl(AnimalRepository animalRepository)
    {
        this.animalRepository = animalRepository;
    }

    @Override
    public Optional<Animal> handle(CreateAnimalCommand command)
    {
        var newAnimal = new Animal(command);
        var createdAnimal = animalRepository.save(newAnimal);
        return Optional.of(createdAnimal);
    }
}
