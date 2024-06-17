package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources;

public record CreateShedResource(String shedName,
                                 String typeShed,
                                 String specie, Long farmId) {
    public CreateShedResource {
        if (shedName == null || shedName.isBlank()) {
            throw new IllegalArgumentException("Shed name cannot be null or empty");
        }
        if (typeShed == null || typeShed.isBlank()) {
            throw new IllegalArgumentException("Type of shed cannot be null or empty");
        }
        if (specie == null || specie.isBlank()) {
            throw new IllegalArgumentException("Specie cannot be null or empty");
        }
    }
}

