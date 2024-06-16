package com.farmlogitech.farmlogitechbackend.profiles.application.internal.comandservices;

import com.farmlogitech.farmlogitechbackend.iam.application.internal.commandservices.UserCommandServiceImpl;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.commands.SignUpCommand;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.entities.Role;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.valueobjects.Roles;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Employee;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateEmployeeCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.services.EmployeeCommandService;
import com.farmlogitech.farmlogitechbackend.profiles.domain.services.ProfileCommandService;
import com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Service
public class EmployeeCommandServiceImpl  implements EmployeeCommandService {
    private final EmployeeRepository employeeRepository;
    private final UserCommandServiceImpl userCommandService;
    private final RoleRepository roleRepository;

    public EmployeeCommandServiceImpl(EmployeeRepository employeeRepository, UserCommandServiceImpl userCommandService, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.userCommandService = userCommandService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Employee> handle(CreateEmployeeCommand command) {
        var role = roleRepository.findByName(Roles.ROLE_FARMWORKER); // Cambiado a ROLE_FARMWORKER
        HashSet<Role> roles = new HashSet<>();
        role.ifPresent(roles::add);
        List<Role> rolesList = new ArrayList<>(roles);
        SignUpCommand signUpCommand = new SignUpCommand(command.username(), command.password(), rolesList);
        Optional<User> userOptional = userCommandService.handle(signUpCommand);
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }
        Employee employee = new Employee(command.name(), command.phone(), command.username(), command.password(), command.position());
        var employeeNew = employeeRepository.save(employee);
        return Optional.of(employeeNew);
    }
}

