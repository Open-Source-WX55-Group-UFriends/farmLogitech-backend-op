package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllExpensesByCategoryAndPeriod;

import java.util.List;

public interface ExpenseQueryService {
    List<Expense>handle(GetAllExpensesByCategoryAndPeriod query);
}
