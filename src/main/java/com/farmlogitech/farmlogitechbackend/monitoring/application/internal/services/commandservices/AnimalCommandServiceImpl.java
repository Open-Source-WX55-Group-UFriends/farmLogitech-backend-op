package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateAnimalCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.DeleteAnimalCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.AnimalCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.AnimalRepository;
import com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnimalCommandServiceImpl implements AnimalCommandService {
    private final AnimalRepository animalRepository;
    private final ExternalFarmService externalFarmService;
    private final EmployeeRepository  employeeRepository;

    public AnimalCommandServiceImpl(AnimalRepository animalRepository, ExternalFarmService externalFarmService, EmployeeRepository employeeRepository)
    {
        this.animalRepository = animalRepository;
        this.externalFarmService = externalFarmService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Animal> handle(CreateAnimalCommand command)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if (!userDetails.isFarmer() && !userDetails.isFarmWorker()) {
            throw new IllegalStateException("Only farmers and workers can create an animal save");
        }

        if(userDetails.isFarmWorker()){
            // Get farmId from the authenticated user's username
            long farmId = employeeRepository.findFarmIdByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new IllegalStateException("No farm found for the given username"));
            var newanimal = new Animal(command.shed(), command.age(), command.location(), command.healthCondition(), command.userId(), farmId);
            var createdAnimal = animalRepository.save(newanimal);
            return Optional.of(createdAnimal);
        }

        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());
        var newAnimal = new Animal(command.shed(), command.age(), command.location(), command.healthCondition(), command.userId(), farmId);
        var createdAnimal = animalRepository.save(newAnimal);
        return Optional.of(createdAnimal);
    }



    @Override
    public Optional<Animal> handle(DeleteAnimalCommand command) {
        long animalId = command.animalId();
        Optional<Animal> animal = animalRepository.findById(animalId);
        if (animal.isPresent()) {
            animalRepository.delete(animal.get());
            return animal;
        }
        return Optional.empty();
    }
}
