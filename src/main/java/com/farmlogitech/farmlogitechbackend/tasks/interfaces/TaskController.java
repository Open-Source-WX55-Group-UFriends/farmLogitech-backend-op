package com.farmlogitech.farmlogitechbackend.tasks.interfaces;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.rest.resources.CreateSubscriptionResource;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.rest.resources.SubscriptionResource;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.tasks.domain.model.aggregates.Task;
import com.farmlogitech.farmlogitechbackend.tasks.domain.services.TaskCommandService;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources.CreateTaskResource;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.resources.TaskResource;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.transform.CreateTaskCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.tasks.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value="/api/v1/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    private final TaskCommandService taskCommandService;

    public TaskController(TaskCommandService taskCommandService) {
        this.taskCommandService = taskCommandService;
    }
    @PostMapping
    public ResponseEntity<TaskResource> createTask(@RequestBody CreateTaskResource resource){
        Optional<Task>task=taskCommandService.handle(CreateTaskCommandFromResourceAssembler.toCommandFromResource(resource));
        return task.map(resp-> new ResponseEntity<>
                (TaskResourceFromEntityAssembler.toResourceFromEntity(resp),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());


    }

}

