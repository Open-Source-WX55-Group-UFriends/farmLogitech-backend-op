package com.farmlogitech.farmlogitechbackend.farms.domain.services;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.CreateFarmCommand;

import java.util.Optional;

public interface FarmCommandService {
    Optional<Farm> createFarm(CreateFarmCommand command);

}
