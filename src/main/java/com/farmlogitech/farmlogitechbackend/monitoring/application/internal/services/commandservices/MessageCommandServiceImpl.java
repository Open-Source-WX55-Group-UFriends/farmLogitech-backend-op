package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Message;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.MessageCommandService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.MessageRepository;
import com.farmlogitech.farmlogitechbackend.monitoring.interfaces.rest.resources.CreateMessageResource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {
    private final MessageRepository messageRepository;

    public MessageCommandServiceImpl(MessageRepository messageRepository)
    {
        this.messageRepository = messageRepository;
    }

    @Override
    public Optional<Message> handle(CreateMessageResource command)
    {
        var newMessage = new Message(command.description(), command.collaboratorId(), command.farmerId());
        var createNewMessage = messageRepository.save(newMessage);
        return Optional.of(createNewMessage);
    }

}
