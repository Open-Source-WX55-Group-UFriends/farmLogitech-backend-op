package com.farmlogitech.farmlogitechbackend.profiles.interfaces.rest.resources;

public record CreateEmployeeResource(String name, String phone, String username, String password, String position, long farmId) {
}
