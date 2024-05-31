package com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.commands;

public record CreateSocialCommand(int id, Integer rating) {

    public CreateSocialCommand {
        if (rating == null) {
            throw new IllegalArgumentException("Price cannot be null or empty");
        }
    }
}
