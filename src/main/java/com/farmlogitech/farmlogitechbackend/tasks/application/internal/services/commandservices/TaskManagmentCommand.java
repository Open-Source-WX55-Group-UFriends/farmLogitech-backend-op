package com.farmlogitech.farmlogitechbackend.tasks.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates.Task;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.DeleteTaskCommand;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.UpdateTaskStatusCommand;
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
        //validation from the aggregate
        task.validateEndDate();
        return Optional.of(task);

    }

    @Override
    public void handle(DeleteTaskCommand command) {
        if(!taskRepository.existsById(command.taskId())){
            throw new IllegalArgumentException("Task doesn't exist");
        }
        try{
            taskRepository.deleteById(command.taskId());
        } catch(Exception e){
            throw new IllegalArgumentException("Error occured while deleting task" + e.getMessage());
        }

    }

    @Override
    public Optional<Task> handle(UpdateTaskStatusCommand command) {
        if(!taskRepository.existsById(command.id())){
            throw new IllegalArgumentException("Task doesn't exist");
        }
        var result = taskRepository.findById(command.id());
        var taskToUpdate = result.get();
        try{
            var updatedTask = taskRepository.save(taskToUpdate.updateStatus(command.status()));
                return Optional.of(updatedTask);
        }catch(Exception e){
            throw new IllegalArgumentException("Error occured while updating task" + e.getMessage());
        }
    }
}
