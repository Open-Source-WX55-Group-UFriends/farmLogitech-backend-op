package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest;

import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Crop;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.DeleteAnimalCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.DeleteCropCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllAnimalsByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllCropQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllCropsByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetCropByIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.CropCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.CropQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.AnimalResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateCropResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CropResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.AnimalResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.CreateCropCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.CropResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crop")
public class CropController {
    private final CropCommandService cropCommandService;
    private final CropQueryService cropQueryService;
    private final ExternalFarmService externalFarmService;

    public CropController(CropCommandService cropCommandService, CropQueryService cropQueryService, ExternalFarmService externalFarmService){
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
        this.externalFarmService = externalFarmService;
    }

    @PostMapping
    public ResponseEntity<CropResource> createCrop(@RequestBody CreateCropResource resource) {
        Optional<Crop> crop = cropCommandService.handle(CreateCropCommandFromResourceAssembler.toCommandFromResource(resource));
        return crop.map(resp ->
                        new ResponseEntity<>(CropResourceFromEntityAssembler
                                .toResourceFromEntity(resp), HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropResource> getCropById(@PathVariable long id) {
        Optional<Crop> crop = cropQueryService.handle(new GetCropByIdQuery(id));
        return crop.map(resp -> ResponseEntity.ok(CropResourceFromEntityAssembler.toResourceFromEntity(resp)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<CropResource>> getAllCrops() {
        var crops = cropQueryService.handle(new GetAllCropQuery());
        if (crops.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var cropResources = crops.stream().map(CropResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(cropResources);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable long id) {
        boolean isDeleted = cropCommandService.handle(new DeleteCropCommand(id)).isPresent();
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filter/all")
    public ResponseEntity<List<CropResource>> getAllCropsByFarmId(){
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        var query = new GetAllCropsByFarmId(farmId);
        var crops = cropQueryService.handle(query);
        var cropResources = crops.stream()
                .filter(crop -> crop.getFarmId() == farmId)
                .map(CropResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(cropResources);
    }
}

