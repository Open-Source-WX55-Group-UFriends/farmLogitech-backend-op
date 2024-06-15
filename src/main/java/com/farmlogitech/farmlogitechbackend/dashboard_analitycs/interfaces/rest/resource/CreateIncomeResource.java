package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateIncomeResource(
        @NotBlank String category,
        @NotBlank String description,
        @Positive double amount,
        @NotNull LocalDate date,
        @NotBlank String period
) {
}