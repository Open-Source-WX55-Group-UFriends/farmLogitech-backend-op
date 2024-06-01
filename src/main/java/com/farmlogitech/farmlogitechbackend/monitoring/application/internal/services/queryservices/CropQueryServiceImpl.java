package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Crop;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllCropQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetCropByIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.CropQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.CropRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropQueryServiceImpl implements CropQueryService {

    private final CropRepository cropRepository;

    public CropQueryServiceImpl(CropRepository cropRepository)
    {
        this.cropRepository = cropRepository;
    }

    @Override
    public Optional<Crop> handle(GetCropByIdQuery query) {
        return cropRepository.findById(query.id());
    }

    @Override
    public List<Crop> handle(GetAllCropQuery query) {
        return cropRepository.findAll();
    }
}
