package com.farmlogitech.farmlogitechbackend.social_interaction.interfaces.interfaces.resources;

public record CreateSocialResource(int id, Integer rating) {
    public CreateSocialResource {
        if (rating == null) {
            throw new IllegalArgumentException("rating cannot be null or empty");
        }
    }
}
