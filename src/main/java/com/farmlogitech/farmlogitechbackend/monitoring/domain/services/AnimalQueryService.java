package com.farmlogitech.farmlogitechbackend.monitoring.domain.services;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllAnimalQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllAnimalsByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAnimalByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AnimalQueryService {
    Optional<Animal> handle(GetAnimalByIdQuery query);
    List<Animal> handle(GetAllAnimalQuery query);
    List<Animal> handle(GetAllAnimalsByFarmId query);
}
