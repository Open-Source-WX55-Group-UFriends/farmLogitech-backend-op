package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllExpensesByCategoryAndDate;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIExpenseCategory;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.ExpenseCommandService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.ExpenseQueryService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.CreateExpenseResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.ExpenseResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.CreateExpenseCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.ExpenseResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/expense", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseController {
    private final ExpenseCommandService expenseCommandService;
    private final ExpenseQueryService expenseQueryService;
    private final ExternalFarmService externalFarmService;

    public ExpenseController(ExpenseCommandService expenseCommandService, ExpenseQueryService expenseQueryService, ExternalFarmService externalFarmService) {
        this.expenseCommandService = expenseCommandService;
        this.expenseQueryService = expenseQueryService;

        this.externalFarmService = externalFarmService;
    }

    @PostMapping
    public ResponseEntity<ExpenseResource> createExpense(@Valid @RequestBody CreateExpenseResource resource) {
        Optional<Expense> expense = expenseCommandService.handle(CreateExpenseCommandFromResourceAssembler.toCommandFromResource(resource));
        return expense.map(resp ->
                        new ResponseEntity<>(ExpenseResourceFromEntityAssembler.toResourceFromEntity(resp), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    @GetMapping("/filter/Category-Date")
    public ResponseEntity<List<ExpenseResource>> getAllExpensesByCategoryAndDate(
            @RequestParam EIExpenseCategory category,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        var query = new GetAllExpensesByCategoryAndDate(category, date);
        var expenses = expenseQueryService.handle(query);
        var expenseResources = expenses.stream()
                .filter(expense -> expense.getFarmId() == farmId) // Filter the expenses by the authenticated user's farmId
                .map(ExpenseResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(expenseResources);
    }
}
