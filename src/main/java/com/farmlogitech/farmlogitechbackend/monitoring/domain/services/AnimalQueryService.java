package com.farmlogitech.farmlogitechbackend.monitoring.domain.services;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllAnimalQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAnimalByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AnimalQueryService {
    Optional<Animal> handle(GetAnimalByIdQuery query);
    List<Animal> handle(GetAllAnimalQuery query);
}
