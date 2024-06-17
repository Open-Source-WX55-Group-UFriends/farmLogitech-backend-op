package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByCategoryAndDate;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByFarmId;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIncomeCategory;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.IncomeCommandService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.IncomeQueryService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.CreateIncomeResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.IncomeResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.CreateIncomeCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.IncomeResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/income", produces = MediaType.APPLICATION_JSON_VALUE)
public class IncomeController {
    private final IncomeCommandService incomeCommandService;
    private final IncomeQueryService incomeQueryService;
    private final ExternalFarmService externalFarmService;

    public IncomeController(IncomeCommandService incomeCommandService, IncomeQueryService incomeQueryService, ExternalFarmService externalFarmService) {
        this.incomeCommandService = incomeCommandService;
        this.incomeQueryService = incomeQueryService;
        this.externalFarmService = externalFarmService;
    }

    @PostMapping
    public ResponseEntity<IncomeResource> createIncome(@Valid @RequestBody CreateIncomeResource resource) {
        Optional<Income> income = incomeCommandService.handle(CreateIncomeCommandFromResourceAssembler.toCommandFromResource(resource));
        return income.map(resp ->
                        new ResponseEntity<>(IncomeResourceFromEntityAssembler.toResourceFromEntity(resp), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    @GetMapping("/filter/Category-Date")
    public ResponseEntity<List<IncomeResource>> getAllIncomesByCategoryAndDate(
            @RequestParam EIncomeCategory category,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        var query = new GetAllIncomesByCategoryAndDate(category, date);
        var incomes = incomeQueryService.handle(query);
        var incomeResources = incomes.stream()
                .filter(income -> income.getFarmId() == farmId) // Filter the incomes by the authenticated user's farmId
                .map(IncomeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(incomeResources);
    }



    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    @GetMapping("/filter/all")
    public ResponseEntity<List<IncomeResource>> getAllIncomesByFarmId(){
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        var query = new GetAllIncomesByFarmId(farmId);
        var incomes = incomeQueryService.handle(query);
        var incomeResources = incomes.stream()
                .filter(income -> income.getFarmId() == farmId) // Filter the incomes by the authenticated user's farmId
                .map(IncomeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(incomeResources);
    }



}