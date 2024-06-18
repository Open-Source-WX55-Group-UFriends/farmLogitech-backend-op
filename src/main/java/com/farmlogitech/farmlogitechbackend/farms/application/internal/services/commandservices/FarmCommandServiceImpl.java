package com.farmlogitech.farmlogitechbackend.farms.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.CreateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.UpdateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.domain.services.FarmCommandService;
import com.farmlogitech.farmlogitechbackend.farms.infrastructure.persistence.jpa.FarmRepository;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Check if the authenticated user is an admin
        if (!userDetails.isFarmer()) {
            throw new IllegalStateException("Only admins can create a farm");
        }

        var farmExists= farmRepository.findByFarmName(command.farmName());
        var farmExistsByProfileId= farmRepository.findByProfileId(userDetails.getId());
        if(!farmExists.isEmpty() || !farmExistsByProfileId.isEmpty()){
            throw new IllegalArgumentException("Farm with name " + command.farmName() + " already exists");
        }
        var newFarm= new Farm(command);
        newFarm.setProfileId(userDetails.getId());
        var createdFarm= farmRepository.save(newFarm);
        return Optional.of(createdFarm);
    }
    @Transactional
    public Optional<Farm> handle(UpdateFarmCommand command) {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long userId = userDetails.getId();

        var farm = farmRepository.findByProfileId(userId).get();

        if (farm.getProfileId() != userId) {
            throw new IllegalStateException("The profileId of Farm is not equal to the id of the authenticated user");
        }

        farm.updateInformation(command);
        farm.setProfileId(userId);
        var updatedFarm = farmRepository.save(farm);
        return Optional.of(updatedFarm);
    }



}
