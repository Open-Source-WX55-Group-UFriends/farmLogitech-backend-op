package com.farmlogitech.farmlogitechbackend.tasks.domain.model.valueobjects.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Collaborator(Long idCollaborator) {
    public Collaborator(){this(null);}
    public Collaborator{
        if(idCollaborator == null){
            throw new IllegalArgumentException("id of collaborator cannot be null");
        }
    }


}
