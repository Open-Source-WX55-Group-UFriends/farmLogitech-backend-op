package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllExpensesByCategoryAndDate;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.ExpenseQueryService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.infrastructure.persistence.jpa.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseQueryServiceImpl implements ExpenseQueryService {
    private final ExpenseRepository expenseRepository;

    public ExpenseQueryServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> handle(GetAllExpensesByCategoryAndDate query) {
        return expenseRepository.findAllByCategoryAndDate(query.category(), query.date());
    }
}