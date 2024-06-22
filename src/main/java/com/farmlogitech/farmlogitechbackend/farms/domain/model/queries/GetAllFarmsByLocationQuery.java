package com.farmlogitech.farmlogitechbackend.farms.domain.model.queries;

public record GetAllFarmsByLocationQuery(String location) {
    public GetAllFarmsByLocationQuery {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("location must not be null or empty");
        }
    }

}
