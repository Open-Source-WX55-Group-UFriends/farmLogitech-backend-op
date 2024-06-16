package com.farmlogitech.farmlogitechbackend.iam.application.internal.commandservices;


import com.farmlogitech.farmlogitechbackend.iam.domain.model.commands.SeedRolesCommand;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.entities.Role;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.valueobjects.Roles;
import com.farmlogitech.farmlogitechbackend.iam.domain.services.RoleCommandService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}
