package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByCategoryAndDate;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByFarmId;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.IncomeQueryService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.infrastructure.persistence.jpa.IncomeRepository;
import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeQueryServiceImpl implements IncomeQueryService {

    private final IncomeRepository incomeRepository;
    private final ExternalFarmService externalFarmService;

    public IncomeQueryServiceImpl(IncomeRepository incomeRepository, ExternalFarmService externalFarmService) {
        this.incomeRepository = incomeRepository;
        this.externalFarmService = externalFarmService;
    }

    @Override
    public List<Income> handle(GetAllIncomesByCategoryAndDate query) {
        return incomeRepository.findAllByCategoryAndDate(query.category(), query.date());
    }


    @Override
    public List<Income> handle(GetAllIncomesByFarmId query) {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        // Fetch the incomes by the authenticated user's farmId
        return incomeRepository.findAllByFarmId(farmId);
    }
}