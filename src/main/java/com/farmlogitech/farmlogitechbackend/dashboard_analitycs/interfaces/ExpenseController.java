package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.ExpenseCommandService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.CreateExpenseResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.ExpenseResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.CreateExpenseCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.ExpenseResourceFromEntityAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value="/api/v1/expense", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseController {
    private final ExpenseCommandService expenseCommandService;

    public ExpenseController(ExpenseCommandService expenseCommandService) {
        this.expenseCommandService = expenseCommandService;
    }
    @PostMapping
    public ResponseEntity<ExpenseResource> createExpense(@RequestBody CreateExpenseResource resource){
        Optional<Expense> expense = expenseCommandService.handle(CreateExpenseCommandFromResourceAssembler.toCommandFromResource(resource));
        return expense.map(resp ->
                new ResponseEntity<>(ExpenseResourceFromEntityAssembler.toResourceFromEntity(resp), CREATED))
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

}
