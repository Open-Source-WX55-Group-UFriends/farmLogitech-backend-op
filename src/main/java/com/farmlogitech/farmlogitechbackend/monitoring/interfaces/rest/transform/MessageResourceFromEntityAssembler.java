package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Message;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.MessageResource;

public class MessageResourceFromEntityAssembler {
    public static MessageResource toResourceFromEntity(Message message) {
        return new MessageResource(
                message.getId(),
                message.getDescription(),
                message.getCollaboratorId(),
                message.getFarmerId(),
                message.getTransmitterId()
        );
    }
}