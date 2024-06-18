package com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.commands;


import java.util.Date;

public record CreateTaskCommand(String description, int time, Date endDate, Long collaboratorId) {  //Be careful with the int and LocalDate

}
