package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.services;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.CreateShedCommand;

import java.util.Optional;

public interface ShedCommandService {
    Optional<Shed> handle(CreateShedCommand command);
}
