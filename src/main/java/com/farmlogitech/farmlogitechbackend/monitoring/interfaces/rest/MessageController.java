package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest;

import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.domain.model.queries.GetAllIncomesByCategoryAndDate;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.resource.IncomeResource;
import com.farmlogitech.farmlogitechbackend.dashboard_analitycs.interfaces.rest.transform.IncomeResourceFromEntityAssembler;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdAndFarmerIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.MessageCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.MessageQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateMessageResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.MessageResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.CreateMessageCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.MessageResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
    private final MessageCommandService messageCommandService;
    private final MessageQueryService messageQueryService;

    public MessageController(MessageCommandService messageCommandService, MessageQueryService messageQueryService) {
        this.messageCommandService = messageCommandService;
        this.messageQueryService = messageQueryService;
    }

    @PostMapping
    public ResponseEntity<MessageResource> createMessage(@RequestBody CreateMessageResource resource) {
        var command = CreateMessageCommandFromResourceAssembler.toCommandFromResource(resource);
        var messageOptional = messageCommandService.handle(command);

        if (messageOptional.isPresent()) {
            var messageResource = MessageResourceFromEntityAssembler.toResourceFromEntity(messageOptional.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(messageResource);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @GetMapping("/collaborator/{collaboratorId}")
    public ResponseEntity<List<MessageResource>> getAllMessagesByCollaboratorId(@PathVariable Long collaboratorId) {
        var query = new GetAllMessagesByCollaboratorIdAndFarmerIdQuery(collaboratorId,collaboratorId);
        var messages = messageQueryService.handle(query);
        var messageResources = messages.stream()
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(messageResources);
    }

    @GetMapping
    public ResponseEntity<List<MessageResource>> getAllMessagesByCollaboradorIdAndFarmerId(@RequestParam  Long collaboradorId, @RequestParam  Long farmerId){
        var getAllMessagesByCollaboratorIdAndFarmerIdQuery  = new GetAllMessagesByCollaboratorIdAndFarmerIdQuery(collaboradorId, farmerId);
        var messages= messageQueryService.handle(getAllMessagesByCollaboratorIdAndFarmerIdQuery);
        var messagesResources= messages.stream().map(MessageResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(messagesResources);
    }






}