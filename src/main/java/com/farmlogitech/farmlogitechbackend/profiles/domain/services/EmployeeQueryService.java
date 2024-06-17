package com.farmlogitech.farmlogitechbackend.profiles.domain.services;

import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Employee;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.queries.GetAllEmployeesByFarmId;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.queries.GetEmployeeByIdQuery;

import java.util.List;
import java.util.Optional;

public interface EmployeeQueryService {
    Optional<Employee> handle(GetEmployeeByIdQuery query);
    List<Employee> handle(GetAllEmployeesByFarmId query);
}
