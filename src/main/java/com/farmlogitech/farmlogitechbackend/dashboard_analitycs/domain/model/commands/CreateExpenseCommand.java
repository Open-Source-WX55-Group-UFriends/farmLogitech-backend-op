package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIExpenseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateExpenseCommand(
        EIExpenseCategory category,
         String description,
         double amount,
         LocalDate date,
         String period
) {
}