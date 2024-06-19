package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.acl.ExternalFarmService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Crop;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Shed;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllShedQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllShedsByFarmId;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetShedByIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.ShedQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.ShedRepository;
import com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ShedQueryServiceImpl implements ShedQueryService {

    private final ShedRepository shedRepository;
    private final ExternalFarmService externalFarmService;
    private final EmployeeRepository employeeRepository;
    public ShedQueryServiceImpl(ShedRepository shedRepository, ExternalFarmService externalFarmService, EmployeeRepository employeeRepository)
    {
        this.shedRepository = shedRepository;
        this.externalFarmService = externalFarmService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Shed> handle(GetShedByIdQuery query) {
        return shedRepository.findById(query.id());
    }

    @Override
    public List<Shed> handle(GetAllShedQuery query) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        boolean hasRequiredRole = authorities.stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_FARMER") || a.getAuthority().equals("ROLE_FARMWORKER"));

        if (!hasRequiredRole) {
            throw new SecurityException("You are not authorized to view these sheds");
        }

        long farmId;
        if (userDetails.isFarmWorker()) {
            farmId = employeeRepository.findFarmIdByUsername(userDetails.getUsername())
                    .orElseThrow(() -> new IllegalStateException("No farm found for the given username"));
        } else if (userDetails.isFarmer()) {
            farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());
        } else {
            throw new IllegalStateException("Only farmers and workers can access a shed");
        }

        return shedRepository.findAllByFarmId(farmId);
    }
    @Override
    public List<Shed> handle(GetAllShedsByFarmId query) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        long farmId = externalFarmService.fetchFarmIdByUserId(userDetails.getId());

        // Check if the authenticated user is the owner of the farm
        if (query.farmId() != farmId) {
            throw new SecurityException("You are not authorized to view these sheds");
        }

        return shedRepository.findAllByFarmId(farmId);
    }

}
