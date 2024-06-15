package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateExpenseCommand(
         String category,
         String description,
         double amount,
         LocalDate date,
         String period
) {
}