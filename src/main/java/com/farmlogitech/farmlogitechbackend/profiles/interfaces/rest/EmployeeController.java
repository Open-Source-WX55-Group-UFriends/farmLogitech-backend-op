package com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.CreateFarmResource;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.FarmResource;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform.CreateFarmCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform.FarmResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.profiles.application.internal.comandservices.EmployeeCommandServiceImpl;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Employee;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateEmployeeCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.services.EmployeeCommandService;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.CreateEmployeeResource;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.CreateProfileResource;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.EmployeeResource;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources.ProfileResource;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.transform.CreateEmployeeCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.transform.CreateProfileCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.transform.EmployeeResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.transform.ProfileResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    private final EmployeeCommandServiceImpl employeeCommandService;

    public EmployeeController(EmployeeCommandServiceImpl employeeCommandService) {
        this.employeeCommandService = employeeCommandService;
    }
    @PreAuthorize("hasRole('ROLE_FARMER')")

    @PostMapping

    public ResponseEntity<EmployeeResource> createFarm(@RequestBody CreateEmployeeResource resource) {
        Optional<Employee> employee = employeeCommandService.handle(CreateEmployeeCommandFromResourceAssembler.toCommandFromResource(resource));
        return employee.map(resp ->
                        new ResponseEntity<>(EmployeeResourceFromEntityAssembler
                                .toResourceFromEntity(resp), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


}