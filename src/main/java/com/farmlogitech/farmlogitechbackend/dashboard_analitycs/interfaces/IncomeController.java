package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.IncomeCommandService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.CreateIncomeResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.IncomeResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.CreateIncomeCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.IncomeResourceFromEntityAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value="/api/v1/income", produces = MediaType.APPLICATION_JSON_VALUE)
public class IncomeController {
    private final IncomeCommandService incomeCommandService;

    public IncomeController(IncomeCommandService incomeCommandService) {
        this.incomeCommandService = incomeCommandService;
    }

    @PostMapping
    public ResponseEntity<IncomeResource> createIncome(@RequestBody CreateIncomeResource resource){
        Optional<Income> income = incomeCommandService.handle(CreateIncomeCommandFromResourceAssembler.toCommandFromResource(resource));
        return income.map(resp ->
                new ResponseEntity<>(IncomeResourceFromEntityAssembler.toResourceFromEntity(resp), CREATED))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }



}
