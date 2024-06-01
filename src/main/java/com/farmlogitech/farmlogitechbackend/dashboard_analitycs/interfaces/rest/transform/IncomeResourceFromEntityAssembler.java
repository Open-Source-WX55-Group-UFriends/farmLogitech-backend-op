package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.IncomeResource;

public class IncomeResourceFromEntityAssembler {
    public static IncomeResource toResourceFromEntity(Income entity){
        return new IncomeResource(entity.getCategory(), entity.getDescription(), entity.getAmount(), entity.getDate(), entity.getPeriod());
    }
}
