package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllExpenseByFarmId;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllExpensesByCategoryAndDate;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByFarmId;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.services.ExpenseQueryService;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.infrastructure.persistence.jpa.ExpenseRepository;
import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseQueryServiceImpl implements ExpenseQueryService {
    private final ExpenseRepository expenseRepository;
    private final ExternalFarmService externalFarmService;

    public ExpenseQueryServiceImpl(ExpenseRepository expenseRepository, ExternalFarmService externalFarmService) {
        this.expenseRepository = expenseRepository;
        this.externalFarmService = externalFarmService;
    }

    @Override
    public List<Expense> handle(GetAllExpensesByCategoryAndDate query) {
        return expenseRepository.findAllByCategoryAndDate(query.category(), query.date());
    }

    @Override
    public List<Expense> handle(GetAllExpenseByFarmId query) {
            // Get the authenticated user
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

            // Fetch the incomes by the authenticated user's farmId
            return expenseRepository.findAllByFarmId(farmId);
        }

}