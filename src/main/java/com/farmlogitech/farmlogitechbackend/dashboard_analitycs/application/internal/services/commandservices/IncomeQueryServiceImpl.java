package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByCategoryAndDate;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.IncomeQueryService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.infrastructure.persistence.jpa.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeQueryServiceImpl implements IncomeQueryService {

    private final IncomeRepository incomeRepository;

    public IncomeQueryServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

 @Override
    public List<Income> handle(GetAllIncomesByCategoryAndDate query) {
       return incomeRepository.findAllIncomeByCategoryAndDate(query.category(),query.date());
    }


}
