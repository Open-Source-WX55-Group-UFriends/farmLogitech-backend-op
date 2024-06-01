package com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateTaskResource(String description, String status, int time, String endDate, Long collaboratorId, Long farmerId) {
}
