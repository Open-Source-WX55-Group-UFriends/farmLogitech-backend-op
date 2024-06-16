package com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.Date;

public record CreateTaskResource(String description, String status, int time, Date endDate, Long collaboratorId, Long farmerId) {
}
