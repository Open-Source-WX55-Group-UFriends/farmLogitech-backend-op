package com.farmlogitech.farmlogitechbackend.farms.domain.services;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.queries.*;

import java.util.List;
import java.util.Optional;


public interface FarmQueryService {
    Optional<Farm> handle(GetFarmByIdQuery query);
    List<Farm> handle(GetAllFarmsByLocationQuery query);
    List<Farm> handle(GetAllFarmsQuery query);
    Optional<Farm> handle(PutFarmById query);
    List<Farm> handle(GetAllFarmByProfileId query);
    Optional<Farm> handle(GetFarmIdByProfileId query);
}