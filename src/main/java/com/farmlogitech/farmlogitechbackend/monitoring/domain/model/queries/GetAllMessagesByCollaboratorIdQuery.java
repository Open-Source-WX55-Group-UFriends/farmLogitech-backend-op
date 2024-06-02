package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries;

public record GetAllMessagesByCollaboratorIdQuery(Long collaboratorId) {
    public GetAllMessagesByCollaboratorIdQuery {
        if (collaboratorId == null || collaboratorId <= 0) {
            throw new IllegalArgumentException("Collaborator ID must be a positive number");
        }
    }
}