package com.farmlogitech.farmlogitechbackend.tasks.domain.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Status(String status) {
    public Status(){this(null);}
}
