package com.farmlogitech.farmlogitechbackend.dashboard_analitycs.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.aggregates.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Integer> {

}
