package com.farmlogitech.farmlogitechbackend.farms.interfaces.acl;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.queries.GetFarmIdByProfileId;
import com.farmlogitech.farmlogitechbackend.farms.domain.services.FarmQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FarmContextFacade {

    private final FarmQueryService farmQueryService;

    public FarmContextFacade(FarmQueryService farmQueryService) {
        this.farmQueryService = farmQueryService;
    }

    public Long findByProfileId(long userId) {
        Optional<Farm> farmOptional = farmQueryService.handle(new GetFarmIdByProfileId(userId));
        return farmOptional.map(farm -> Long.valueOf(farm.getId())).orElse(null);
    }
}
