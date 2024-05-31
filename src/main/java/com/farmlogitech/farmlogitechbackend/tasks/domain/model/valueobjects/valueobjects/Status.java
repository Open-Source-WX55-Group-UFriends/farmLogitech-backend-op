package com.farmlogitech.farmlogitechbackend.tasks.domain.model.valueobjects.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Status(String status) {
    public Status(){this(null);}
}
