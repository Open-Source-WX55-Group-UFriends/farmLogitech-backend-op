package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllShedQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetShedByIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.ShedQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.ShedRepository;

import java.util.List;
import java.util.Optional;

public class ShedQueryServiceImpl implements ShedQueryService {

    private final ShedRepository shedRepository;

    public ShedQueryServiceImpl(ShedRepository shedRepository)
    {
        this.shedRepository = shedRepository;
    }

    @Override
    public Optional<Shed> handle(GetShedByIdQuery query) {
        return shedRepository.findById(query.id());
    }

    @Override
    public List<Shed> handle(GetAllShedQuery query) {
        return shedRepository.findAll();
    }
}
