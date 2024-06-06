package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByCategoryAndDate;

import java.util.List;

public interface IncomeQueryService {
    List<Income> handle(GetAllIncomesByCategoryAndDate query);

}
