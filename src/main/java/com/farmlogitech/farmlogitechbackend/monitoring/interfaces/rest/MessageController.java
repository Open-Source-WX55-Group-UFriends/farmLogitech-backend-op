package com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.MessageQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.MessageResource;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.transform.MessageResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
    private final MessageQueryService messageQueryService;

    public MessageController(MessageQueryService messageQueryService) {
        this.messageQueryService = messageQueryService;
    }

    @GetMapping("/collaborator/{collaboratorId}")
    public ResponseEntity<List<MessageResource>> getAllMessagesByCollaboratorId(@PathVariable Long collaboratorId) {
        var query = new GetAllMessagesByCollaboratorIdQuery(collaboratorId);
        var messages = messageQueryService.handle(query);
        var messageResources = messages.stream()
                .map(MessageResourceAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(messageResources);
    }
}