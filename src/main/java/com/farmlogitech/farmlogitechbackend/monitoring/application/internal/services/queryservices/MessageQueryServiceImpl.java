package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Message;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdAndFarmerIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetMessageByIdAndUserIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.MessageQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.MessageRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {
    private final MessageRepository messageRepository;

    public MessageQueryServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> handle(GetAllMessagesByCollaboratorIdQuery query) {
        return messageRepository.findAllByCollaboratorId(query.collaboratorId());
    }
    @Override
    public List<Message> handle(GetAllMessagesByCollaboratorIdAndFarmerIdQuery query) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long authenticatedUserId = userDetails.getId();

        List<Message> messages;
        if (userDetails.isFarmWorker()) {
            messages = messageRepository.findAllMessageByCollaboratorIdAndTransmitterIdNot(authenticatedUserId, authenticatedUserId);
        return messages;
        } else if (userDetails.isFarmer()) {
            messages = messageRepository.findAllMessageByFarmerIdAndTransmitterIdNot(authenticatedUserId, authenticatedUserId);
            return messages;
        } else {
            throw new SecurityException("Authenticated user is neither collaborator nor farmer");
        }

    }


    @Override
    public Optional<Message> handle(GetMessageByIdAndUserIdQuery query) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long authenticatedUserId = userDetails.getId();

        Optional<Message> messageOptional = messageRepository.findById(query.messageId());

        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            if (authenticatedUserId.equals(message.getCollaboratorId()) || authenticatedUserId.equals(message.getFarmerId())) {
                return messageOptional;
            } else {
                throw new SecurityException("Authenticated user is neither collaborator nor farmer");
            }
        } else {
            throw new IllegalArgumentException("Message with provided ID does not exist");
        }
    }
}