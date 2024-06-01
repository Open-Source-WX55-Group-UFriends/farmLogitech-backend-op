package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Crop;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateCropCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.CropCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.CropRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CropCommandServiceImpl implements CropCommandService {
    private CropRepository cropRepository;

    public CropCommandServiceImpl(CropRepository cropRepository)
    {
        this.cropRepository = cropRepository;
    }

    @Override
    public Optional<Crop> handle(CreateCropCommand command)
    {
        var newCrop = new Crop(command);
        var createdCrop = cropRepository.save(newCrop);
        return Optional.of(createdCrop);
    }
}
