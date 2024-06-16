package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands.CreateExpenseCommand;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.CreateExpenseResource;

public class CreateExpenseCommandFromResourceAssembler {
    public static CreateExpenseCommand toCommandFromResource(CreateExpenseResource resource) {
        return new CreateExpenseCommand(
                resource.category(),
                resource.description(),
                resource.amount(),
                resource.date(),
                resource.period(),
                resource.farmId()
        );
    }
}