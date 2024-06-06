package com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries;

public record GetShedByIdQuery(Long id) {
    public GetShedByIdQuery {
        if (id == null || id == 0)
        {
            throw new IllegalArgumentException("id must be grater than zero");
        }
    }
}
