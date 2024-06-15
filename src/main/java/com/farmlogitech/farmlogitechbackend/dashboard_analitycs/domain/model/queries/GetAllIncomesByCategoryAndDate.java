package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;

import java.time.LocalDate;

public record GetAllIncomesByCategoryAndDate(Income.IncomeCategory category, LocalDate date) {
}