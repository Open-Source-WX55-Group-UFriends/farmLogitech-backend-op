package com.farmlogitech.farmlogitechbackend.tasks.domain.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Description(String description) {
    public Description(){this(null);}

}
