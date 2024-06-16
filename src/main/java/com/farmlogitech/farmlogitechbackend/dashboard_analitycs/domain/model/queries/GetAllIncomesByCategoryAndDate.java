package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIncomeCategory;

import java.time.LocalDate;

public record GetAllIncomesByCategoryAndDate(EIncomeCategory category, LocalDate date) {
}