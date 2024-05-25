package com.farmlogitech.farmlogitechbackend.farms.interfaces.rest;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.queries.GetAllFarmByLocationQuery;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.queries.GetAllFarmsQuery;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.queries.GetFarmByIdQuery;
import com.farmlogitech.farmlogitechbackend.farms.domain.services.FarmCommandService;
import com.farmlogitech.farmlogitechbackend.farms.domain.services.FarmQueryService;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.CreateFarmResource;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.FarmResource;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform.CreateFarmCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform.FarmResourceFromEntityAssembler;
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
    @GetMapping("/farm/{location}")

    private ResponseEntity<List<FarmResource>> getAllByLocation(String location) {
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


}
