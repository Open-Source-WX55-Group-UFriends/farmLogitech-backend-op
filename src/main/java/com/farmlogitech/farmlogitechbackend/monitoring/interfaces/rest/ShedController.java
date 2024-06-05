package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllShedQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetShedByIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.ShedCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.ShedQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateShedResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.ShedResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.CreateShedCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.ShedResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/shed")
public class ShedController {
    private final ShedCommandService shedCommandService;
    private final ShedQueryService shedQueryService;

    public ShedController(ShedCommandService shedCommandService, ShedQueryService shedQueryService) {
        this.shedCommandService = shedCommandService;
        this.shedQueryService = shedQueryService;
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
        Optional<Shed> shed = shedQueryService.handle(new GetShedByIdQuery(id));
        return shed.map(resp -> ResponseEntity.ok(ShedResourceFromEntityAssembler.toResourceFromEntity(resp)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShedResource>> getAllSheds() {
        var sheds = shedQueryService.handle(new GetAllShedQuery());
        if (sheds.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var shedResources = sheds.stream().map(ShedResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(shedResources);
    }
}
