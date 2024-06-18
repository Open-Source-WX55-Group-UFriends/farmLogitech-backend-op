package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Crop;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllShedQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllShedsByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetShedByIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.ShedQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.ShedRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShedQueryServiceImpl implements ShedQueryService {

    private final ShedRepository shedRepository;
    private final ExternalFarmService externalFarmService;

    public ShedQueryServiceImpl(ShedRepository shedRepository, ExternalFarmService externalFarmService)
    {
        this.shedRepository = shedRepository;
        this.externalFarmService = externalFarmService;
    }

    @Override
    public Optional<Shed> handle(GetShedByIdQuery query) {
        return shedRepository.findById(query.id());
    }

    @Override
    public List<Shed> handle(GetAllShedQuery query) {
        return shedRepository.findAll();
    }

    @Override
    public List<Shed> handle(GetAllShedsByFarmId query) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        return shedRepository.findAllByFarmId(farmId);
    }
}
