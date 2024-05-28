package com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.resources;

public record CreateSubscriptionResource (int id, Integer price, String description, Boolean paid) {

    public CreateSubscriptionResource {
        if (price == null) {
            throw new IllegalArgumentException("price cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("description cannot be null or empty");
        }
        if (paid == null) {
            throw new IllegalArgumentException("paid cannot be null or empty");
        }
    }
}
