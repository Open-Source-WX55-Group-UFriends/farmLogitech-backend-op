package com.farmlogitech.farmlogitechbackend.monitoring.domain.services;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Crop;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllCropQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllCropsByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetCropByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CropQueryService {
    Optional<Crop> handle(GetCropByIdQuery query);
    List<Crop> handle(GetAllCropQuery query);
    List<Crop> handle(GetAllCropsByFarmId query);
}
