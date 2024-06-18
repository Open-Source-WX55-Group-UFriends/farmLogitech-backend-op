package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIncomeCategory;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateIncomeCommand(
        @NotNull EIncomeCategory category,
         String description,
         double amount,
         LocalDate date,
         String period) {}