package com.farmlogitech.farmlogitechbackend.tasks.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates.Task;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.commands.CreateTaskCommand;
import com.farmlogitech.farmlogitechbackend.tasks.domain.services.TaskCommandService;
import com.farmlogitech.farmlogitechbackend.tasks.infrastructure.persistance.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TaskManagmentCommand implements TaskCommandService {
    private final TaskRepository taskRepository;

    public TaskManagmentCommand(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> handle(CreateTaskCommand command) {

        var task= new Task(command);
        taskRepository.save(task);
        return Optional.of(task);

    }
}
