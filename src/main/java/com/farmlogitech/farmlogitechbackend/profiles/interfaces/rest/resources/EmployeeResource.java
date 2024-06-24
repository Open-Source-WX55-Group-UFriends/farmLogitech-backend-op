package com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources;

import com.farmlogitech.farmlogitechbackend.iam.domain.model.aggregates.User;

public record EmployeeResource(Long Id, String name, String phone, String username, String password, String position, long farmId, long farmerId) {
}
