package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable

public record  SubscriptionId(Long subscriptionId) {
    public SubscriptionId {
        if (subscriptionId < 0) {
            throw new IllegalArgumentException("Subscription  cannot be negative");
        }
    }
    public SubscriptionId() {
        this(0L);
    }
}
