package com.farmlogitech.farmlogitechbackend.tasks.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Employee;
import com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates.Task;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.DeleteTaskCommand;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.UpdateTaskStatusCommand;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.commands.CreateTaskCommand;
import com.farmlogitech.farmlogitechbackend.tasks.domain.services.TaskCommandService;
import com.farmlogitech.farmlogitechbackend.tasks.infrastructure.persistance.jpa.repositories.TaskRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TaskManagmentCommand implements TaskCommandService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public TaskManagmentCommand(TaskRepository taskRepository, EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Optional<Task> handle(CreateTaskCommand command) {
        if (command.endDate() == null) {
            throw new IllegalArgumentException("End date cannot be null");
        }

        if (!employeeRepository.existsById(command.collaboratorId())) {
            throw new IllegalArgumentException("Collaborator ID does not exist in employees Id");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Optional<Employee> employeeOpt = employeeRepository.findById(command.collaboratorId());
        if (employeeOpt.isEmpty() || !userDetails.getId().equals(employeeOpt.get().getFarmId())) {
            throw new IllegalArgumentException("Authenticated user's ID does not match the farmer ID");
        }

        var task= new Task(command);
        task.setFarmerId(userDetails.getId());
        task.setStatus("PENDING");
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
            // Set the status to "DONE"
            var updatedTask = taskRepository.save(taskToUpdate.updateStatus("FINISHED"));
            return Optional.of(updatedTask);
        }catch(Exception e){
            throw new IllegalArgumentException("Error occured while updating task" + e.getMessage());
        }
    }
}
