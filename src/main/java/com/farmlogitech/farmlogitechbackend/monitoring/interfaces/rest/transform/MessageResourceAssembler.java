package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.message;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.MessageResource;

public class MessageResourceAssembler {
    public static MessageResource toResource(message message) {
        return new MessageResource(
                message.getId(),
                message.getDescription(),
                message.getCollaboratorId(),
                message.getFarmerId()
        );
    }
}