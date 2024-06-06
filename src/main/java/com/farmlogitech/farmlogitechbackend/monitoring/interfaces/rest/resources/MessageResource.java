package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources;

public record MessageResource(Long id, String description, Long collaboratorId, Long farmerId) {
}