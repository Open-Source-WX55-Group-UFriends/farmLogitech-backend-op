package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries;

public record GetAllMessagesByCollaboratorIdAndFarmerIdQuery(Long userId, Long transmitterId) {
    public GetAllMessagesByCollaboratorIdAndFarmerIdQuery {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Collaborator ID must be a positive number");
        }
        if (transmitterId == null || transmitterId <= 0) {
            throw new IllegalArgumentException("Farmer ID must be a positive number");
        }
    }
}