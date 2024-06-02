package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateShedCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.ShedCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.ShedRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShedCommandServiceImpl implements ShedCommandService {
    private ShedRepository shedRepository;

    public ShedCommandServiceImpl(ShedRepository shedRepository)
    {
        this.shedRepository = shedRepository;
    }

    @Override
    public Optional<Shed> handle(CreateShedCommand command)
    {
        var newShed = new Shed(command);
        var createdShed = shedRepository.save(newShed);
        return Optional.of(createdShed);
    }
}
