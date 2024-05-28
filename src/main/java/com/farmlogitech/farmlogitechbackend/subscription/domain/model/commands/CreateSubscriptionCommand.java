package com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands;

public record CreateSubscriptionCommand(
        int id, Integer price, String description, Boolean paid){

    public CreateSubscriptionCommand {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null or empty");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (paid == null) {
            throw new IllegalArgumentException("Paid cannot be null or empty");
        }
    }
}
