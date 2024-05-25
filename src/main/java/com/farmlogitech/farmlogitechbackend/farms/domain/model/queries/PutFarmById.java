package com.farmlogitech.farmlogitechbackend.farms.domain.model.queries;

public record PutFarmById(Integer id) {
    public PutFarmById {
        if (id == null || id == 0) {
            throw new IllegalArgumentException("id must be greater than zero");
        }
    }
}