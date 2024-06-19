package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest;

import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllCropsByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllShedQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllShedsByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetShedByIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.ShedCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.ShedQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateShedResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CropResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.ShedResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.CreateShedCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.CropResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.ShedResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/shed")
public class ShedController {
    private final ShedCommandService shedCommandService;
    private final ShedQueryService shedQueryService;
    private final ExternalFarmService externalFarmService;
    private final EmployeeRepository employeeRepository;

    public ShedController(ShedCommandService shedCommandService, ShedQueryService shedQueryService, ExternalFarmService externalFarmService, EmployeeRepository employeeRepository) {
        this.shedCommandService = shedCommandService;
        this.shedQueryService = shedQueryService;
        this.externalFarmService = externalFarmService;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping
    public ResponseEntity<ShedResource> createShed(@RequestBody CreateShedResource resource) {
        Optional<Shed> shed = shedCommandService.handle(CreateShedCommandFromResourceAssembler.toCommandFromResource(resource));
        return shed.map(resp ->
                        new ResponseEntity<>(ShedResourceFromEntityAssembler
                                .toResourceFromEntity(resp), HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShedResource> getShedById(@PathVariable long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId;

        if (userDetails.isFarmWorker()) {
            farmId = employeeRepository.findFarmIdByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new IllegalStateException("No farm found for the given username"));
        } else if (userDetails.isFarmer()) {
            farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());
        } else {
            throw new IllegalStateException("Only farmers and workers can access a shed");
        }

        Optional<Shed> shed = shedQueryService.handle(new GetShedByIdQuery(id));
        return shed
                .filter(s -> s.getFarmId() == farmId)
                .map(resp -> ResponseEntity.ok(ShedResourceFromEntityAssembler.toResourceFromEntity(resp)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    /*@GetMapping("/all")
    public ResponseEntity<List<ShedResource>> getAllSheds() {
        var sheds = shedQueryService.handle(new GetAllShedQuery());
        if (sheds.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var shedResources = sheds.stream().map(ShedResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(shedResources);
    }*/

    @GetMapping("/filter/all")
    public ResponseEntity<List<ShedResource>> getAllShedsByFarmId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId;

        if (userDetails.isFarmWorker()) {
            farmId = employeeRepository.findFarmIdByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new IllegalStateException("No farm found for the given username"));
        } else if (userDetails.isFarmer()) {
            farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());
        } else {
            throw new IllegalStateException("Only farmers and workers can access a shed");
        }

        var query = new GetAllShedQuery();
        var sheds = shedQueryService.handle(query);
        var shedResources = sheds.stream()
                .filter(shed -> shed.getFarmId() == farmId)
                .map(ShedResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(shedResources);
    }

}

