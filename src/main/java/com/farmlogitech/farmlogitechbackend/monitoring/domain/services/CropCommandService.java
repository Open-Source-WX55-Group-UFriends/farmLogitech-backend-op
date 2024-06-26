package com.farmlogitech.farmlogitechbackend.monitoring.domain.services;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Crop;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateCropCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.DeleteCropCommand;

import java.util.Optional;

public interface CropCommandService {
    Optional<Crop> handle(CreateCropCommand command);

    Optional<Crop> handle(DeleteCropCommand command);

}
