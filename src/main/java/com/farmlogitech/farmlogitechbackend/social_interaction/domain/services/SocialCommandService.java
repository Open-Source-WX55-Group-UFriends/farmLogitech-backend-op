package com.farmlogitech.farmlogitechbackend.social_interaction.domain.services;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates.Social;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.commands.CreateSocialCommand;

import java.util.List;
import java.util.Optional;

public interface SocialCommandService {
    Optional<Social> handle(CreateSocialCommand command);
}
