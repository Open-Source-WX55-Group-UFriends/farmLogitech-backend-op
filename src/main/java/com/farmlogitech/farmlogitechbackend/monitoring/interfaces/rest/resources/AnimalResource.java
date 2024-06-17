package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources;

public record AnimalResource(long id,
                             String shed,
                             Integer age,
                             String location,
                             String healthCondition, Long userId, Long farmId) {
}

