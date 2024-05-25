package com.farmlogitech.farmlogitechbackend.farms.domain.model.queries;

public record GetAllFarmByLocationQuery(String location) {
    public GetAllFarmByLocationQuery {
        if (location == null || location.isBlank()) {
            throw new IllegalArgumentException("location must not be null or empty");
        }
    }

}
