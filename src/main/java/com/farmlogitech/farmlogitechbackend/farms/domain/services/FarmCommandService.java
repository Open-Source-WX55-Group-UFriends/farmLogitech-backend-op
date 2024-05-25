package com.farmlogitech.farmlogitechbackend.farms.domain.services;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.CreateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.UpdateFarmCommand;

import java.util.Optional;

public interface FarmCommandService {
    Optional<Farm> handle(CreateFarmCommand command);
    Optional<Farm> handle(UpdateFarmCommand command);

}
