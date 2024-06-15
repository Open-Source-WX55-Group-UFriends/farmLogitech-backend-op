package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIExpenseCategory;

import java.time.LocalDate;

public record ExpenseResource(
        EIExpenseCategory category,
        String description,
        double amount,
        LocalDate date,
        String period,
        long farmId
) {
}