package com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.commands;

import java.time.LocalDate;

public record CreateTaskCommand(String description, String status, int time, LocalDate endDate) {  //Be careful with the int and LocalDate

}
