package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands.CreateIncomeCommand;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.CreateIncomeResource;

public class CreateIncomeCommandFromResourceAssembler {
    public static CreateIncomeCommand toCommandFromResource(CreateIncomeResource resource) {
        return new CreateIncomeCommand(
                resource.category(),
                resource.description(),
                resource.amount(),
                resource.date(),
                resource.period()
        );
    }
}