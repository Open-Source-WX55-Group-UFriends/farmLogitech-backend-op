package com.farmlogitech.farmlogitechbackend.farms.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.CreateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.domain.services.FarmCommandService;
import com.farmlogitech.farmlogitechbackend.farms.infrastructure.persistence.jpa.FarmRepository;
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
}
