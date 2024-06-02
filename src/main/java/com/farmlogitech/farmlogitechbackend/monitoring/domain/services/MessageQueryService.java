package com.farmlogitech.farmlogitechbackend.monitoring.domain.services;

import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.aggregates.message;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdAndFarmerIdQuery;
import com.farmlogitech.farmlogitechbackend.monitoring.domain.model.queries.GetAllMessagesByCollaboratorIdQuery;

import java.util.List;

public interface MessageQueryService {
    List<message> handle(GetAllMessagesByCollaboratorIdQuery query);
    List<message> handle(GetAllMessagesByCollaboratorIdAndFarmerIdQuery query);
}