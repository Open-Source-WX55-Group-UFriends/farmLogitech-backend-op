package com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources;

public record TaskResource(
        Long taskId, String description, String status, int time, String endDate, Long collaboratorId, Long farmerId
) {

}
