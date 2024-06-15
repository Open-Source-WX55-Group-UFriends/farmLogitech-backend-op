package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Expense;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findAllByCategoryAndDate(EIExpenseCategory category, LocalDate date);
}