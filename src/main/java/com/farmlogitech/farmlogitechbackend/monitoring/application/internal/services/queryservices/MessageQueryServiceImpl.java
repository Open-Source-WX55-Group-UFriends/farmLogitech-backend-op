package com.farmlogitech.farmlogitechbackend.monitoring.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Message;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdAndFarmerIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.services.MessageQueryService;
import com.farmlogitech.farmlogitechbackend.monitoring.infrastructure.persistence.jpa.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return messageRepository.findAllByCollaboratorIdAndFarmerId(query.collaboratorId(), query.farmerId());
    }
}