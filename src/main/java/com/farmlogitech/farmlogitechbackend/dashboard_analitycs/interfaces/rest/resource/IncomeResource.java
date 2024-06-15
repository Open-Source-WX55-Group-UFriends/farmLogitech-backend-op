package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIncomeCategory;

import java.time.LocalDate;

public record IncomeResource(
      EIncomeCategory category,
        String description,
        double amount,
        LocalDate date,
        String period,
        long farmId
) {
}