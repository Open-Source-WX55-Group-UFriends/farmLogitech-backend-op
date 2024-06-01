package com.farmlogitech.farmlogitechbackend.social_interaction.interfaces.interfaces.transform;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates.Social;
import com.farmlogitech.farmlogitechbackend.social_interaction.interfaces.interfaces.resources.SocialResource;

public class SocialResourceFromEntityAssembler {
    public static SocialResource toResourceFromEntity(Social entity)
    {
        return new SocialResource(entity.getId(), entity.getRating(), entity.getProfileId(), entity.getFarmId());
    }
}
