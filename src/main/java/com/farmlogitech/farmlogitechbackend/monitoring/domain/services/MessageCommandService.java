package com.farmlogitech.farmlogitechbackend.monitoring.domain.services;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Message;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateMessageResource;

import java.util.Optional;

public interface MessageCommandService {
    Optional<Message> handle(CreateMessageResource command);
}
