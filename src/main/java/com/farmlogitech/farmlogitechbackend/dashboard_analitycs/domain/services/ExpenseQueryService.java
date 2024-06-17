package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllExpenseByFarmId;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllExpensesByCategoryAndDate;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExpenseQueryService {
    List<Expense>handle(GetAllExpensesByCategoryAndDate query);
    List<Expense>handle(GetAllExpenseByFarmId query);

}
