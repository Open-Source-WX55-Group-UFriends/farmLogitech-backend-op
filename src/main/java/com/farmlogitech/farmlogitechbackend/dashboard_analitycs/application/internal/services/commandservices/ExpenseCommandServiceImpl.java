package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands.CreateExpenseCommand;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIExpenseCategory;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.ExpenseCommandService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.infrastructure.persistence.jpa.ExpenseRepository;
import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class ExpenseCommandServiceImpl implements ExpenseCommandService {
    private final ExpenseRepository expenseRepository;
    private final ExternalFarmService externalFarmService;

    public ExpenseCommandServiceImpl(ExpenseRepository expenseRepository, ExternalFarmService externalFarmService) {
        this.expenseRepository = expenseRepository;
        this.externalFarmService = externalFarmService;
    }


    @PreAuthorize("hasAuthority('ROLE_FARMER')")

    @Override
    public Optional<Expense> handle(CreateExpenseCommand command) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());
        if (command.amount() <= 0) {
            throw new IllegalArgumentException("The income amount must be greater than zero");
        }
        if (command.category() != EIExpenseCategory.SERVICES && command.category() != EIExpenseCategory.SUPPLIES && command.category() != EIExpenseCategory.OTHER && command.category() != EIExpenseCategory.LABOR && command.category() != EIExpenseCategory.MAINTENANCE) {
            throw new IllegalArgumentException("Invalid category. Category must be SERVICES, SUPPLIES, LABOR, MAINTENANCE, or OTHER.");
        }
        var expense = new Expense(command);
        expense.setFarmId(farmId);
        var createdExpense = expenseRepository.save(expense);
        return Optional.of(createdExpense);
    }
}
