package com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.commands;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.valueobjects.valueobjects.Collaborator;

import java.time.LocalDate;

public record CreateTaskCommand(String description, String status, int time, LocalDate endDate, Long idCollaborator) {  //Be careful with the int and LocalDate

}
