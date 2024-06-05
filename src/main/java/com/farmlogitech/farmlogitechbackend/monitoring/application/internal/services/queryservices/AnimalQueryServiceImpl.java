package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllAnimalQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAnimalByIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.AnimalQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalQueryServiceImpl implements AnimalQueryService {

    private final AnimalRepository animalRepository;

    public AnimalQueryServiceImpl(AnimalRepository animalRepository)
    {
        this.animalRepository = animalRepository;
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
}
