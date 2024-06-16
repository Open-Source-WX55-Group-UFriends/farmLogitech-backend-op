package com.farmlogitech.farmlogitechbackend.profiles.domain.services;

import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Employee;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateEmployeeCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateProfileCommand;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface EmployeeCommandService {
     Optional<Employee> handle(CreateEmployeeCommand command);

}
