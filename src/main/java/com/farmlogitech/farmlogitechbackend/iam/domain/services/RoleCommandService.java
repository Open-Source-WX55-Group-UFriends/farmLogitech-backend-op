package com.farmlogitech.farmlogitechbackend.iam.domain.services;


import com.farmlogitech.farmlogitechbackend.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
