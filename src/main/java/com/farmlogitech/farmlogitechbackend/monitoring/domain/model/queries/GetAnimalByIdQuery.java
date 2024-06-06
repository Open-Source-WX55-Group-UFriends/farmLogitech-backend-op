package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries;

public record GetAnimalByIdQuery(Long id) {
    public GetAnimalByIdQuery {
        if (id == null || id == 0)
        {
            throw new IllegalArgumentException("id must be grater than zero");
        }
    }
}
