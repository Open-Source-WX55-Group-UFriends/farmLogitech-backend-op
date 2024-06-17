package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.valueobjects.EIncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {
    List<Income> findAllByCategoryAndDate(EIncomeCategory category, LocalDate date);
    List<Income> findAllByFarmId(long farmId);

}