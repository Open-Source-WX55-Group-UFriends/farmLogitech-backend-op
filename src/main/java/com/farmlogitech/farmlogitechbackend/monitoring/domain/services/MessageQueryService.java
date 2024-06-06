package com.farmlogitech.farmlogitechbackend.monitoring.domain.services;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.Message;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdAndFarmerIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdQuery;

import java.util.List;

public interface MessageQueryService {
    List<Message> handle(GetAllMessagesByCollaboratorIdQuery query);
    List<Message> handle(GetAllMessagesByCollaboratorIdAndFarmerIdQuery query);
}