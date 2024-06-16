package com.farmlogitech.farmlogitechbackend.monitoring.domain.services;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Message;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdAndFarmerIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetMessageByIdAndUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface MessageQueryService {
    List<Message> handle(GetAllMessagesByCollaboratorIdQuery query);
    List<Message> handle(GetAllMessagesByCollaboratorIdAndFarmerIdQuery query);
    Optional<Message>handle(GetMessageByIdAndUserIdQuery query);
}