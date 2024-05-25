package com.farmlogitech.farmlogitechbackend.farms.domain.services;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.queries.GetFarmByIdQuery;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface FarmQueryService {
    Optional<Farm> handle(GetFarmByIdQuery query);

}
