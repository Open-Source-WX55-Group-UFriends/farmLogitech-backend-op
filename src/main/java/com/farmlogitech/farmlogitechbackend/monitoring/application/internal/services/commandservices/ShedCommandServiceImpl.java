package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateShedCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.ShedCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.ShedRepository;
import com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShedCommandServiceImpl implements ShedCommandService {
    private final ShedRepository shedRepository;
    private final ExternalFarmService externalFarmService;
    private final EmployeeRepository employeeRepository;

    public ShedCommandServiceImpl(ShedRepository shedRepository, ExternalFarmService externalFarmService, EmployeeRepository employeeRepository)
    {
        this.shedRepository = shedRepository;
        this.externalFarmService = externalFarmService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Shed> handle(CreateShedCommand command) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if (!userDetails.isFarmer() && !userDetails.isFarmWorker()) {
            throw new IllegalStateException("Only farmers and workers can create a shed save");
        }

        Optional<Shed> existingShed = shedRepository.findByShedName(command.shedName());
        if (existingShed.isPresent()) {
            throw new IllegalStateException("A shed with this name already exists");
        }

        if(userDetails.isFarmWorker()){
            // Get farmId from the authenticated user's username
            long farmId = employeeRepository.findFarmIdByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new IllegalStateException("No farm found for the given username"));
            var newShed = new Shed(command.shedName(), command.typeShed(), command.specie(), command.userId(), farmId);
            var createdShed = shedRepository.save(newShed);
            return Optional.of(createdShed);
        }

        // Get farmId from the authenticated user's username
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        var newShed = new Shed(command.shedName(), command.typeShed(), command.specie(), command.userId(), farmId);
        var createdShed = shedRepository.save(newShed);
        return Optional.of(createdShed);
    }

}
