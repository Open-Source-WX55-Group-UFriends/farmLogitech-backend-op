package com.farmlogitech.farmlogitechbackend.profiles.application.internal.queryservices;

import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Employee;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.queries.GetAllEmployeesByFarmId;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.queries.GetEmployeeByIdQuery;
import com.farmlogitech.farmlogitechbackend.profiles.domain.services.EmployeeQueryService;
import com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class EmployeeQueryServiceImpl implements EmployeeQueryService {

    private final EmployeeRepository employeeRepository;

    public EmployeeQueryServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Optional<Employee> handle(GetEmployeeByIdQuery query) {
        return employeeRepository.findById(query.id());
    }
    @Override
    public List<Employee> handle(GetAllEmployeesByFarmId query) {
        return employeeRepository.findAllByFarmId(query.farmId());
    }
    @Override
    public List<Employee> searchEmployees(String term, long farmId) {
        return employeeRepository.findByNameContainingOrUsernameContainingAndFarmId(term, term, farmId);
    }
}
