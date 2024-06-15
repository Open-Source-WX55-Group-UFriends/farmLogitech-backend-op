package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.IncomeResource;

public class IncomeResourceFromEntityAssembler {
    public static IncomeResource toResourceFromEntity(Income income) {
        return new IncomeResource(
                income.getCategory(),
                income.getDescription(),
                income.getAmount(),
                income.getDate(),
                income.getPeriod(),
                income.getFarmId()
        );
    }
}