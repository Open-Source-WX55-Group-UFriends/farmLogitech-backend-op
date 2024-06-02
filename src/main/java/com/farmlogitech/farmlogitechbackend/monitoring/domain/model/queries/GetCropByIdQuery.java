package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries;

public record GetCropByIdQuery(Long id) {
    public GetCropByIdQuery {
        if (id == null || id == 0)
        {
            throw new IllegalArgumentException("id must be grater than zero");
        }
    }
}
