package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.services;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Crop;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateCropCommand;

import java.util.Optional;

public interface CropCommandService {
    Optional<Crop> handle(CreateCropCommand command);
}
