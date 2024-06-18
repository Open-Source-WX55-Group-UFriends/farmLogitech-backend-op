package com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands;

public record UpdateTaskStatusCommand(String status, Long  id) {
}
