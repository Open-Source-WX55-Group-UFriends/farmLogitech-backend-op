package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateShedCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.ShedCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.ShedRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShedCommandServiceImpl implements ShedCommandService {
    private final ShedRepository shedRepository;
    private final ExternalFarmService externalFarmService;

    public ShedCommandServiceImpl(ShedRepository shedRepository, ExternalFarmService externalFarmService)
    {
        this.shedRepository = shedRepository;
        this.externalFarmService = externalFarmService;
    }

    @Override
    public Optional<Shed> handle(CreateShedCommand command)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if (!userDetails.isFarmer()) {
            throw new IllegalStateException("Only farmers and workers can create a shed save");
        }

        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());
        var newShed = new Shed(command.shedName(), command.typeShed(), command.specie(), command.userId(), farmId);
        var createdShed = shedRepository.save(newShed);
        return Optional.of(createdShed);
    }
}
