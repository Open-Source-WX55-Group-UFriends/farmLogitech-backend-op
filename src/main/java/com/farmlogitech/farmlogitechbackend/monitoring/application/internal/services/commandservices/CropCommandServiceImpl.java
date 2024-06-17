package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
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
    private final CropRepository cropRepository;
    private final ExternalFarmService externalFarmService;


    public CropCommandServiceImpl(CropRepository cropRepository, ExternalFarmService externalFarmService)
    {
        this.cropRepository = cropRepository;
        this.externalFarmService = externalFarmService;
    }

    @Override
    public Optional<Crop> handle(CreateCropCommand command)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if (!userDetails.isFarmer()) {
            throw new IllegalStateException("Only farmers and workers can create a crop save");
        }

        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());
        var newCrop = new Crop(command.shed(), command.typeCrop(), command.seedtime(), command.userId(), farmId);
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
