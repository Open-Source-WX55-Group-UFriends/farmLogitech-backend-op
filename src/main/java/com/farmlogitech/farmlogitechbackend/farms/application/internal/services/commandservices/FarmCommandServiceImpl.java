package com.farmlogitech.farmlogitechbackend.farms.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.CreateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.UpdateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.domain.services.FarmCommandService;
import com.farmlogitech.farmlogitechbackend.farms.infrastructure.persistence.jpa.FarmRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FarmCommandServiceImpl implements FarmCommandService {
    private FarmRepository farmRepository;

    public FarmCommandServiceImpl(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public Optional<Farm> handle(CreateFarmCommand command) {
        if(farmRepository.existsById(command.id()))
                throw  new IllegalArgumentException("Farm already exists");
        var newFarm= new Farm(command);
        var createdFarm= farmRepository.save(newFarm);
        return Optional.of(createdFarm);

    }

    @Transactional
    public Optional<Farm> handle(UpdateFarmCommand command) {
        Optional<Farm> existingFarmOptional = farmRepository.findById(command.id());

        if (existingFarmOptional.isEmpty()) {
            throw new IllegalArgumentException("Farm with id " + command.id() + " does not exist");
        }

        Farm existingFarm = existingFarmOptional.get();

        existingFarm.setFarmName(command.farmName());
        existingFarm.setLocation(command.location());
        existingFarm.setType(command.type());
        existingFarm.setInfrastructure(command.infrastructure());
        existingFarm.setServices(command.services());
        existingFarm.setStatus(command.status());
        existingFarm.setCertificates(command.certificates());
        existingFarm.setImage(command.image());

        Farm savedFarm = farmRepository.save(existingFarm);
        return Optional.of(savedFarm);
    }
}
