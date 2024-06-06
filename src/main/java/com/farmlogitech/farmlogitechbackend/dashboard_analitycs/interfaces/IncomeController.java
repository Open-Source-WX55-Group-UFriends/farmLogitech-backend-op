package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllExpensesByCategoryAndDate;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByCategoryAndDate;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.IncomeCommandService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.IncomeQueryService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.CreateIncomeResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.ExpenseResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.IncomeResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.CreateIncomeCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.ExpenseResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.IncomeResourceFromEntityAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value="/api/v1/income", produces = MediaType.APPLICATION_JSON_VALUE)
public class IncomeController {
    private final IncomeCommandService incomeCommandService;
    private final IncomeQueryService incomeQueryService;

    public IncomeController(IncomeCommandService incomeCommandService, IncomeQueryService incomeQueryService) {
        this.incomeCommandService = incomeCommandService;
        this.incomeQueryService = incomeQueryService;
    }

    @PostMapping
    public ResponseEntity<IncomeResource> createIncome(@RequestBody CreateIncomeResource resource){
        Optional<Income> income = incomeCommandService.handle(CreateIncomeCommandFromResourceAssembler.toCommandFromResource(resource));
        return income.map(resp ->
                new ResponseEntity<>(IncomeResourceFromEntityAssembler.toResourceFromEntity(resp), CREATED))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<IncomeResource>> getAllIncomeByCategoryAndDate(@RequestParam  String category, @RequestParam  String date){
        var getAllIncomeByCategoryAndDateQuery = new GetAllIncomesByCategoryAndDate(category, date);
        var incomes= incomeQueryService.handle(getAllIncomeByCategoryAndDateQuery);
        var incomesResources= incomes.stream().map(IncomeResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(incomesResources);

    }


}
