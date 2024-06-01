package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands.CreateIncomeCommand;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;
public interface IncomeCommandService {
    Optional<Income> handle(CreateIncomeCommand command);
}
