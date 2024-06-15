package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;

import java.time.LocalDate;

public record IncomeResource(
        Income.IncomeCategory category,
        String description,
        double amount,
        LocalDate date,
        String period
) {
}