package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands;

public record CreateMessageCommand(String description, Long collaboratorId, Long farmerId) {
}
