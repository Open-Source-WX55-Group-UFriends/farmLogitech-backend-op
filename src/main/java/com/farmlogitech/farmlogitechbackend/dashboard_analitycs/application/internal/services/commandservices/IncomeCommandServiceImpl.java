package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.commands.CreateIncomeCommand;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIncomeCategory;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.IncomeCommandService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.infrastructure.persistence.jpa.IncomeRepository;
import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IncomeCommandServiceImpl implements IncomeCommandService {
    private final IncomeRepository incomeRepository;
    private final ExternalFarmService externalFarmService;

    public IncomeCommandServiceImpl(IncomeRepository incomeRepository, ExternalFarmService externalFarmService) {
        this.incomeRepository = incomeRepository;
        this.externalFarmService = externalFarmService;
    }


    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    @Override
    public Optional<Income> handle(CreateIncomeCommand command) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        if (command.amount() <= 0) {
            throw new IllegalArgumentException("The income amount must be greater than zero");
        }
        if (command.category() != EIncomeCategory.SALES && command.category() != EIncomeCategory.SUBSIDES && command.category() != EIncomeCategory.OTHER) {
            throw new IllegalArgumentException("Invalid category. Category must be SALES, SUBSIDES, or OTHER.");
        }
        var income = new Income(command);
        income.setFarmId(farmId);
        var createdIncome = incomeRepository.save(income);
        return Optional.of(createdIncome);
    }
}
