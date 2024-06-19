package com.farmlogitech.farmlogitechbackend.tasks.interfaces;

import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.profiles.infrastructure.persistence.jpa.repositories.EmployeeRepository;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates.Task;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.DeleteTaskCommand;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.queries.GetAllTaksByFarmerIdQuery;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.queries.GetAllTasksByCollaboratorIdQuery;
import com.farmlogitech.farmlogitechbackend.tasks.domain.services.TaskCommandService;
import com.farmlogitech.farmlogitechbackend.tasks.domain.services.TaskQueryService;
import com.farmlogitech.farmlogitechbackend.tasks.infrastructure.persistance.jpa.repositories.TaskRepository;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources.CreateTaskResource;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources.TaskResource;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.transform.CreateTaskCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.transform.UpdateTaskCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value="/api/v1/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;
    private final EmployeeRepository employeesrepository;
    private final TaskRepository taskRepository;


    public TaskController(TaskCommandService taskCommandService, TaskQueryService taskQueryService, EmployeeRepository employeesrepository, TaskRepository taskRepository) {
        this.taskCommandService = taskCommandService;
        this.taskQueryService = taskQueryService;
        this.employeesrepository = employeesrepository;
        this.taskRepository = taskRepository;
    }

    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    @PostMapping
    public ResponseEntity<TaskResource> createTask(@RequestBody CreateTaskResource resource){
        Optional<Task>task=taskCommandService.handle(CreateTaskCommandFromResourceAssembler.toCommandFromResource(resource));
        return task.map(resp-> new ResponseEntity<>
                (TaskResourceFromEntityAssembler.toResourceFromEntity(resp),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());


    }
    @GetMapping("/all/farmer/me")
    public ResponseEntity<List<TaskResource>> getAllTaskByAuthenticatedFarmer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long farmerId = userDetails.getId();

        var tasks = taskQueryService.handle(new GetAllTaksByFarmerIdQuery(farmerId));
        if (tasks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var taskResources = tasks.stream().map(TaskResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(taskResources);
    }
    @GetMapping("/all/collaborator/me")
    public ResponseEntity<?> getAllTaskByAuthenticatedCollaborator() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Long> optionalCollaboratorId = employeesrepository.findCollaboratorIdByUsername(username);
        if (!optionalCollaboratorId.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No collaborator found with username: " + username);
        }

        Long collaboratorId = optionalCollaboratorId.get();

        var tasks = taskQueryService.handle(new GetAllTasksByCollaboratorIdQuery(collaboratorId));
        if (tasks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tasks found for the authenticated collaborator with ID: " + collaboratorId);
        }
        var taskResources = tasks.stream().map(TaskResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(taskResources);
    }




    @PutMapping("/update/{taskId}")
    public ResponseEntity<TaskResource> updateTaskStatus(@PathVariable Long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Long> optionalCollaboratorId = employeesrepository.findCollaboratorIdByUsername(username);
        if (!optionalCollaboratorId.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No collaborator found with username: " + username);

            return null;
        }

        Long collaboratorId = optionalCollaboratorId.get();

        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("No task found with id: " + taskId);
            return null;
        }

        Task task = taskOptional.get();
        if (!task.getCollaboratorId().equals(collaboratorId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        var updateTaskStatusCommand = UpdateTaskCommandFromResourceAssembler.toCommandFromResource(taskId);
        var updatedTask = taskCommandService.handle(updateTaskStatusCommand);
        if(updatedTask.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(updatedTask.get());
        return ResponseEntity.ok(taskResource);
    }



    /*
@DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId){
    var deleteTaskCommand = new DeleteTaskCommand(taskId);
    taskCommandService.handle(deleteTaskCommand);
    return ResponseEntity.ok("Task given id successfully deleted");
}

*/


}

