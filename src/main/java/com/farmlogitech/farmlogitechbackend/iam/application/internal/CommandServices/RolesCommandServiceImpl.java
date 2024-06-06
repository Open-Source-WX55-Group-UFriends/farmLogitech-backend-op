package com.farmlogitech.farmlogitechbackend.iam.application.internal.CommandServices;

import com.farmlogitech.farmlogitechbackend.iam.domain.model.commands.SeddRolesCommand;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.entities.Role;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.valueobjects.Roles;
import com.farmlogitech.farmlogitechbackend.iam.domain.services.RoleCommandServices;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RolesCommandServiceImpl implements RoleCommandServices {
    private final RoleRepository roleRepository;

    public RolesCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void handle(SeddRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}
