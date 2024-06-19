package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByFarmId;
import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllAnimalQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllAnimalsByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAnimalByIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.AnimalQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.AnimalRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalQueryServiceImpl implements AnimalQueryService {

    private final AnimalRepository animalRepository;
    private final ExternalFarmService externalFarmService;

    public AnimalQueryServiceImpl(AnimalRepository animalRepository, ExternalFarmService externalFarmService) {
        this.animalRepository = animalRepository;
        this.externalFarmService = externalFarmService;
    }

    @Override
    public Optional<Animal> handle(GetAnimalByIdQuery query)
    {
        return animalRepository.findById(query.id());
    }

    @Override
    public List<Animal> handle(GetAllAnimalQuery query) {
        return animalRepository.findAll();
    }

    @Override
    public List<Animal> handle(GetAllAnimalsByFarmId query) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        return animalRepository.findAllByFarmId(farmId);
    }
}
