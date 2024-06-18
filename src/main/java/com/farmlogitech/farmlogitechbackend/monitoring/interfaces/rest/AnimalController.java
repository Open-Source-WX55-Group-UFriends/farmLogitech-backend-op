package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByFarmId;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.IncomeResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.IncomeResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Animal;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.commands.DeleteAnimalCommand;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllAnimalQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllAnimalsByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAnimalByIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.AnimalCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.AnimalQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.AnimalResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateAnimalResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.AnimalResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.CreateAnimalCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/animal")
public class AnimalController {
    private final AnimalCommandService animalCommandService;
    private final AnimalQueryService animalQueryService;
    private final ExternalFarmService externalFarmService;

    public AnimalController(AnimalCommandService animalCommandService, AnimalQueryService animalQueryService, ExternalFarmService externalFarmService) {
        this.animalCommandService = animalCommandService;
        this.animalQueryService = animalQueryService;
        this.externalFarmService = externalFarmService;
    }

    @PostMapping
    public ResponseEntity<AnimalResource> createAnimal(@RequestBody CreateAnimalResource resource) {
        Optional<Animal> animal = animalCommandService.handle(CreateAnimalCommandFromResourceAssembler.toCommandFromResource(resource));
        return animal.map(resp ->
                        new ResponseEntity<>(AnimalResourceFromEntityAssembler
                                .toResourceFromEntity(resp), HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResource> getAnimalById(@PathVariable long id) {
        Optional<Animal> animal = animalQueryService.handle(new GetAnimalByIdQuery(id));
        return animal.map(resp -> ResponseEntity.ok(AnimalResourceFromEntityAssembler.toResourceFromEntity(resp)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnimalResource>> getAllAnimal() {
        var animals = animalQueryService.handle(new GetAllAnimalQuery());
        if(animals.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        var animalResources = animals.stream().map(AnimalResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(animalResources);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable long id) {
        boolean isDeleted = animalCommandService.handle(new DeleteAnimalCommand(id)).isPresent();
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/filter/all")
    public ResponseEntity<List<AnimalResource>> getAllAnimalsByFarmId(){
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        var query = new GetAllAnimalsByFarmId(farmId);
        var animals = animalQueryService.handle(query);
        var incomeResources = animals.stream()
                .filter(income -> income.getFarmId() == farmId)
                .map(AnimalResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(incomeResources);
    }
}

