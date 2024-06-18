package com.farmlogitech.farmlogitechbackend.monitoring.domain.services;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllAnimalsByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllShedQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllShedsByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetShedByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ShedQueryService {
    Optional<Shed> handle(GetShedByIdQuery query);
    List<Shed> handle(GetAllShedQuery query);
    List<Shed> handle(GetAllShedsByFarmId query);

}
