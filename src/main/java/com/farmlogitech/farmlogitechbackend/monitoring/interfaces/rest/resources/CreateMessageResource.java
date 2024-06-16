package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources;

public record CreateMessageResource(String description, Long collaboratorId, Long farmerId, Long transmitterId) {
}
