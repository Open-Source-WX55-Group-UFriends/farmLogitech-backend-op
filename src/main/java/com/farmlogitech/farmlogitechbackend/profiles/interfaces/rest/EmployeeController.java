package com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.CreateFarmResource;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.resources.FarmResource;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform.CreateFarmCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.farms.interfaces.rest.transform.FarmResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.profiles.application.internal.comandservices.EmployeeCommandServiceImpl;
import com.farmlogitech.farmlogitechbackend.profiles.application.internal.queryservices.EmployeeQueryServiceImpl;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Employee;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateEmployeeCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.commands.CreateProfileCommand;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.queries.GetAllEmployeesByFarmId;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.queries.GetEmployeeByIdQuery;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/v1/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    private final EmployeeCommandServiceImpl employeeCommandService;
    private final ExternalFarmService externalFarmService;
    private final EmployeeQueryServiceImpl employeeQueryService;

    public EmployeeController(EmployeeCommandServiceImpl employeeCommandService, ExternalFarmService externalFarmService, EmployeeQueryServiceImpl employeeQueryService) {
        this.employeeCommandService = employeeCommandService;
        this.externalFarmService = externalFarmService;
        this.employeeQueryService = employeeQueryService;
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

    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResource> getEmployeeByFarmId(@PathVariable long id) {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        Optional<Employee> employeeOptional = employeeQueryService.handle(new GetEmployeeByIdQuery(id));

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            if (employee.getFarmId() == farmId) {
                var employeeResource = EmployeeResourceFromEntityAssembler.toResourceFromEntity(employee);
                return ResponseEntity.ok(employeeResource);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    @GetMapping("/all")
    public ResponseEntity<List<EmployeeResource>> getAllEmployeesByFarmId() {
        // Get the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        var query = new GetAllEmployeesByFarmId(farmId);
        var employees = employeeQueryService.handle(query);
        var employeeResources = employees.stream()
                .map(EmployeeResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(employeeResources);
    }

}