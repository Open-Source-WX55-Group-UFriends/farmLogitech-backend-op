package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands.CreateIncomeCommand;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIncomeCategory;
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
        if (command.amount() <= 0) {
            throw new IllegalArgumentException("The income amount must be greater than zero");
        }
        if (command.category() != EIncomeCategory.SALES && command.category() != EIncomeCategory.SUBSIDES && command.category() != EIncomeCategory.OTHER) {
            throw new IllegalArgumentException("Invalid category. Category must be SALES, SUBSIDES, or OTHER.");
        }
        var income = new Income(command);
        var createdIncome = incomeRepository.save(income);
        return Optional.of(createdIncome);
    }
}
