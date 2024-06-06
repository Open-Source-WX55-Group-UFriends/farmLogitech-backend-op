package com.farmlogitech.farmlogitechbackend.farms.interfaces.rest;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.commands.UpdateFarmCommand;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.queries.*;
import com.farmlogitech.farmlogitechbackend.farms.domain.services.FarmCommandService;
import com.farmlogitech.farmlogitechbackend.farms.domain.services.FarmQueryService;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.CreateFarmResource;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.FarmResource;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.UpdateFarmResource;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform.CreateFarmCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform.FarmResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform.UpdateFarmCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/farm")
public class FarmController {
    private final FarmCommandService farmCommandService;
    private final FarmQueryService farmQueryService;


    public FarmController(FarmCommandService farmCommandService, FarmQueryService farmQueryService) {
        this.farmCommandService = farmCommandService;
        this.farmQueryService = farmQueryService;
    }
    @PostMapping
    public ResponseEntity<FarmResource> createFarm(@RequestBody CreateFarmResource resource) {
        Optional<Farm> farm = farmCommandService.handle(CreateFarmCommandFromResourceAssembler.toCommandFromResource(resource));
        return farm.map(resp ->
                        new ResponseEntity<>(FarmResourceFromEntityAssembler
                                .toResourceFromEntity(resp), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @GetMapping("/{id}")
    public ResponseEntity<FarmResource>getFarmById(@PathVariable int id){
        Optional<Farm> farm = farmQueryService.handle(new GetFarmByIdQuery(id));
        return farm.map(resp->ResponseEntity.ok(FarmResourceFromEntityAssembler.toResourceFromEntity(resp)))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @GetMapping("/location/{location}")
    private ResponseEntity<List<FarmResource>> getAllByLocation(@PathVariable String location) {
        var getAllFarmByLocation= new GetAllFarmByLocationQuery(location);
        var farms = farmQueryService.handle(getAllFarmByLocation);
        if(farms.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var farmResources= farms.stream().map(FarmResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(farmResources);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FarmResource>> getAllFarms() {
        var farms = farmQueryService.handle(new GetAllFarmsQuery());
        if(farms.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var farmResources = farms.stream().map(FarmResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(farmResources);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FarmResource> updateFarm(@PathVariable int id, @RequestBody UpdateFarmResource resource) {
        if (id != resource.id()) {
            throw new IllegalArgumentException("ID in path and ID in request body must be the same");
        }

        UpdateFarmCommand command = UpdateFarmCommandFromResourceAssembler.toCommandFromResource(resource);
        Optional<Farm> updatedFarmOptional = farmCommandService.handle(command);

        return updatedFarmOptional
                .map(updatedFarm -> ResponseEntity.ok(FarmResourceFromEntityAssembler.toResourceFromEntity(updatedFarm)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/all/farms/profile/{profileId}")
    public ResponseEntity<List<FarmResource>>getAllFarmsByProfileId(@PathVariable long profileId) {
        var farms = farmQueryService.handle(new GetAllFarmByProfileId(profileId));
        if(farms.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var farmResources = farms.stream().map(FarmResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(farmResources);
    }


}
