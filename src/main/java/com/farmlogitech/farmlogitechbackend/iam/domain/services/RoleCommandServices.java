package com.farmlogitech.farmlogitechbackend.iam.domain.services;

import com.farmlogitech.farmlogitechbackend.iam.domain.model.commands.SeddRolesCommand;

public interface RoleCommandServices {
    void handle(SeddRolesCommand command);
}
