package com.farmlogitech.farmlogitechbackend.social_interaction.application.internal.services.commandservices;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates.Social;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.commands.CreateSocialCommand;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.services.SocialCommandService;
import com.farmlogitech.farmlogitechbackend.social_interaction.infrastructure.persistence.jpa.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocialCommandServiceImpl implements SocialCommandService {

    private final SocialRepository socialRepository;

    public SocialCommandServiceImpl(SocialRepository socialRepository)
    {
        this.socialRepository = socialRepository;
    }

    @Override
    public Optional<Social> handle(CreateSocialCommand command){
        if(socialRepository.existsById(command.id()))
            throw new IllegalArgumentException("Social alredy exists");
        var newSocial = new Social(command);
        var createdSocial = socialRepository.save(newSocial);
        return Optional.of(createdSocial);
    }
}
