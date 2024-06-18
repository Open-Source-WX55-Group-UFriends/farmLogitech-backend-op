package com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.commands;


import java.time.LocalDate;
import java.util.Date;

public record CreateTaskCommand(String description, String status, int time, Date endDate, Long collaboratorId) {  //Be careful with the int and LocalDate

}
