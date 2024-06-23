package com.farmlogitech.farmlogitechbackend.profiles.domain.services;

import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Employee;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.queries.GetAllEmployeesByFarmId;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.queries.GetEmployeeByIdQuery;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.queries.GetEmployeeMeByUsername;

import java.util.List;
import java.util.Optional;

public interface EmployeeQueryService {
    Optional<Employee> handle(GetEmployeeByIdQuery query);
    List<Employee> handle(GetAllEmployeesByFarmId query);
    List<Employee> searchEmployees(String term, long farmId);
    Optional<Employee> handle(GetEmployeeMeByUsername query);

}
