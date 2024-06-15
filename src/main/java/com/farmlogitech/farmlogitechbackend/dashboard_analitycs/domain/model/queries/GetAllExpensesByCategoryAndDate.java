package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIExpenseCategory;

import java.time.LocalDate;

public record GetAllExpensesByCategoryAndDate(EIExpenseCategory category, LocalDate date) {
}