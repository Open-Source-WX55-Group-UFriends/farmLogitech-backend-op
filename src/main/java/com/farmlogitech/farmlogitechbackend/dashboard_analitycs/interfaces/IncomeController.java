package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByCategoryAndDate;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIncomeCategory;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.IncomeCommandService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.IncomeQueryService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.CreateIncomeResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.IncomeResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.CreateIncomeCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.IncomeResourceFromEntityAssembler;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    public IncomeController(IncomeCommandService incomeCommandService, IncomeQueryService incomeQueryService) {
        this.incomeCommandService = incomeCommandService;
        this.incomeQueryService = incomeQueryService;
    }

    @PostMapping
    public ResponseEntity<IncomeResource> createIncome(@Valid @RequestBody CreateIncomeResource resource) {
        Optional<Income> income = incomeCommandService.handle(CreateIncomeCommandFromResourceAssembler.toCommandFromResource(resource));
        return income.map(resp ->
                        new ResponseEntity<>(IncomeResourceFromEntityAssembler.toResourceFromEntity(resp), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/filter/Category-Date")

    public ResponseEntity<List<IncomeResource>> getAllIncomesByCategoryAndDate(
            @RequestParam EIncomeCategory category,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        var query = new GetAllIncomesByCategoryAndDate(category, date);
        var incomes = incomeQueryService.handle(query);
        var incomeResources = incomes.stream()
                .map(IncomeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(incomeResources);
    }
}