package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands.CreateIncomeCommand;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.IncomeCommandService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.infrastructure.persistence.jpa.IncomeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncomeCommandServiceImpl implements IncomeCommandService {
    private final IncomeRepository incomeRepository;

    public IncomeCommandServiceImpl(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }


    @Override
    public Optional<Income> handle(CreateIncomeCommand command) {
        var Income= new Income(command);
        var createdIncome= incomeRepository.save(Income);
        return Optional.of(createdIncome);

    }



}
