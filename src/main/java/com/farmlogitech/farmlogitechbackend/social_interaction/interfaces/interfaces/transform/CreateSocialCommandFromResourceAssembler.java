package com.farmlogitech.farmlogitechbackend.social_interaction.interfaces.interfaces.transform;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.commands.CreateSocialCommand;
import com.farmlogitech.farmlogitechbackend.social_interaction.interfaces.interfaces.resources.CreateSocialResource;

public class CreateSocialCommandFromResourceAssembler {
    public static CreateSocialCommand toCommandFromResource (CreateSocialResource resource)
    {
        return new CreateSocialCommand( resource.rating(), resource.farmId());
    }
}
