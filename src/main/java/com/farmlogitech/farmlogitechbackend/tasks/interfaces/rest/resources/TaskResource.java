package com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources;

public record TaskResource(
        String description, String status, int time, String endDate, Long collaboratorId, Long farmerId
) {

}
