package com.farmlogitech.farmlogitechbackend.farms.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.queries.GetAllFarmByLocationQuery;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.queries.GetAllFarmsQuery;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.queries.GetFarmByIdQuery;
import com.farmlogitech.farmlogitechbackend.farms.domain.services.FarmQueryService;
import com.farmlogitech.farmlogitechbackend.farms.infrastructure.persistence.jpa.FarmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class FarmQueryServiceImpl implements FarmQueryService {

    private final FarmRepository farmRepository;

    public FarmQueryServiceImpl(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    @Override
    public Optional<Farm> handle(GetFarmByIdQuery query) {
        return farmRepository.findById(query.id());
    }

    @Override
    public List<Farm> handle(GetAllFarmByLocationQuery query) {
        return farmRepository.findAllByLocation(query.location());
    }

    @Override
    public List<Farm> handle(GetAllFarmsQuery query) {
        return farmRepository.findAll();
    }
}
