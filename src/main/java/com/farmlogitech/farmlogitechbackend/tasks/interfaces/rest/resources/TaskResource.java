package com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources;

import java.util.Date;

public record TaskResource(
        Long taskId, String description, String status, int time, Date endDate, Long collaboratorId, Long farmerId
) {

}
