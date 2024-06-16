package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Crop;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateCropCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.DeleteAnimalCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.DeleteCropCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.CropCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.CropRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if (!userDetails.isFarmer()) {
            throw new IllegalStateException("Only farmers and workers can create a crop save");
        }
        if (!userDetails.isFarmWorker()) {
            throw new IllegalStateException("Only farmers and workers can create a crop save");
        }

        var newCrop = new Crop(command);
        var createdCrop = cropRepository.save(newCrop);
        return Optional.of(createdCrop);
    }

    @Override
    public Optional<Crop> handle(DeleteCropCommand command) {
        long cropId = command.cropId();
        Optional<Crop> crop = cropRepository.findById(cropId);
        if (crop.isPresent()) {
            cropRepository.delete(crop.get());
            return crop;
        }
        return Optional.empty();
    }
}
