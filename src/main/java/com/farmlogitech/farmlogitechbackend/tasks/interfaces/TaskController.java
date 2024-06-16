package com.farmlogitech.farmlogitechbackend.tasks.interfaces;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates.Task;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.commands.DeleteTaskCommand;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.queries.GetAllTaksByFarmerIdQuery;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.queries.GetAllTasksByCollaboratorIdAndFarmerIdQuery;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.queries.GetAllTasksByCollaboratorIdQuery;
import com.farmlogitech.farmlogitechbackend.tasks.domain.services.TaskCommandService;
import com.farmlogitech.farmlogitechbackend.tasks.domain.services.TaskQueryService;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources.CreateTaskResource;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources.TaskResource;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.transform.CreateTaskCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value="/api/v1/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;

    public TaskController(TaskCommandService taskCommandService, TaskQueryService taskQueryService) {
        this.taskCommandService = taskCommandService;
        this.taskQueryService = taskQueryService;
    }
    @PostMapping
    public ResponseEntity<TaskResource> createTask(@RequestBody CreateTaskResource resource){
        Optional<Task>task=taskCommandService.handle(CreateTaskCommandFromResourceAssembler.toCommandFromResource(resource));
        return task.map(resp-> new ResponseEntity<>
                (TaskResourceFromEntityAssembler.toResourceFromEntity(resp),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());


    }
    @GetMapping("/all/farmer/{farmerId}")
    public ResponseEntity<List<TaskResource>> getAllTaskByFarmerId(@PathVariable Long farmerId){
        var tasks=taskQueryService.handle(new GetAllTaksByFarmerIdQuery(farmerId));
        if(tasks.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var taskResources=tasks.stream().map(TaskResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(taskResources);
    }

    @GetMapping("/all/collaborator/{collaboratorId}")
    public ResponseEntity<List<TaskResource>> getAllTaskByCollaboratorId(@PathVariable Long collaboratorId){
        var tasks=taskQueryService.handle(new GetAllTasksByCollaboratorIdQuery(collaboratorId));
        if(tasks.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var taskResources=tasks.stream().map(TaskResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(taskResources);
    }

@DeleteMapping("/delete/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId){
    var deleteTaskCommand = new DeleteTaskCommand(taskId);
    taskCommandService.handle(deleteTaskCommand);
    return ResponseEntity.ok("Task given id successfully deleted");
}

    /*
    // IMPLEMENT IN THE FUTURE @Rod
    @GetMapping("/all/search/collaborator/{collaboratorId}")
    public ResponseEntity<List<TaskResource>> getAllTaskByCollaboratorAndFarmer(@PathVariable Long farmerId,Long collaboratorId){
        var tasks=taskQueryService.handle(new GetAllTasksByCollaboratorIdAndFarmerIdQuery(farmerId,collaboratorId));
        if(tasks.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var taskResources=tasks.stream().map(TaskResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(taskResources);
    }

*/








}

