package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries;

public record GetAllMessagesByCollaboratorIdAndFarmerIdQuery(Long collaboratorId, Long farmerId) {
    public GetAllMessagesByCollaboratorIdAndFarmerIdQuery {
        if (collaboratorId == null || collaboratorId <= 0) {
            throw new IllegalArgumentException("Collaborator ID must be a positive number");
        }
        if (farmerId == null || farmerId <= 0) {
            throw new IllegalArgumentException("Farmer ID must be a positive number");
        }
    }
}