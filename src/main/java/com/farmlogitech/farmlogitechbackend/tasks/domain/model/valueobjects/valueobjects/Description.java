package com.farmlogitech.farmlogitechbackend.tasks.domain.model.valueobjects.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Description(String description) {
    public Description(){this(null);}

}
