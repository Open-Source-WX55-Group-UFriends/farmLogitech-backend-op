package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;

import java.time.LocalDate;

public record GetAllExpensesByCategoryAndDate(Expense.ExpenseCategory category, LocalDate date) {
}