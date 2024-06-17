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
        if(!farmExists.isEmpty()){
            throw new IllegalArgumentException("Farm with name " + command.farmName() + " already exists");
        }
        var newFarm= new Farm(command);
        newFarm.setProfileId(userDetails.getId());
        var createdFarm= farmRepository.save(newFarm);
        return Optional.of(createdFarm);
    }
    @Transactional
    public Optional<Farm> handle(UpdateFarmCommand command) {
        if (!farmRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Farm with id " + command.id() + " does not exist");
        }

        var farm=farmRepository.findById(command.id()).get();
        farm.updateInformation(command);
        var updatedFarm = farmRepository.save(farm);
        return Optional.of(updatedFarm);

    }
}
