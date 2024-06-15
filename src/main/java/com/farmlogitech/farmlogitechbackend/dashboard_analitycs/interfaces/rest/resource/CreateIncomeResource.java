package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIncomeCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateIncomeResource(
         EIncomeCategory category,
         String description,
         double amount,
         LocalDate date,
         String period
) {
}