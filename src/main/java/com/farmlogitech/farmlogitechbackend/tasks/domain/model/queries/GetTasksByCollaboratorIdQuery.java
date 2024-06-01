package com.farmlogitech.farmlogitechbackend.tasks.domain.model.queries;

import com.farmlogitech.farmlogitechbackend.tasks.domain.model.valueobjects.valueobjects.Collaborator;

public record GetTasksByCollaboratorIdQuery(Collaborator idCollaborator) {

}
