package com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.transform;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.UpdateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.valueobjects.ProfileId;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.resources.UpdateSubscriptionResource;

public class UpdateSubscriptionCommandFromResourceAssembler {
    public static UpdateSubscriptionCommand toCommandFromResource(ProfileId profileId, UpdateSubscriptionResource resource)
    {
        return new UpdateSubscriptionCommand(profileId,
                resource.firstName(), resource.lastName(), resource.direction(), resource.phone(),
                resource.gender(), resource.birthDate(), resource.documentNumber(),
                resource.documentType(), resource.role(), resource.price(), resource.description(),
                resource.paid());
    }
}
