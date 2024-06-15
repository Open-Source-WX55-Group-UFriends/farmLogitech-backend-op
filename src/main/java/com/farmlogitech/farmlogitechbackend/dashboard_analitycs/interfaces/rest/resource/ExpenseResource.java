package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;

import java.time.LocalDate;

public record ExpenseResource(
        Expense.ExpenseCategory category,
        String description,
        double amount,
        LocalDate date,
        String period
) {
}