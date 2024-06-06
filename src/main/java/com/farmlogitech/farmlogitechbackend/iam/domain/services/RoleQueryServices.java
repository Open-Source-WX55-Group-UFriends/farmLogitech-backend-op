package com.farmlogitech.farmlogitechbackend.iam.domain.services;

import com.farmlogitech.farmlogitechbackend.iam.domain.model.entities.Role;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.queries.GetAllRolesQuery;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryServices {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
