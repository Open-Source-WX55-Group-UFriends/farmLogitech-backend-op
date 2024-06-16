package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.ExpenseResource;

public class ExpenseResourceFromEntityAssembler {
    public static ExpenseResource toResourceFromEntity(Expense expense) {
        return new ExpenseResource(
                expense.getCategory(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getDate(),
                expense.getPeriod(),
                expense.getFarmId()
        );
    }
}