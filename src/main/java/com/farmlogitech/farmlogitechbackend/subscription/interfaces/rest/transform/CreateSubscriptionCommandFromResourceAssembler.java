package com.farmlogitech.farmlogitechbackend.subscription.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.rest.resources.CreateSubscriptionResource;

public class CreateSubscriptionCommandFromResourceAssembler {
    public static CreateSubscriptionCommand toCommandFromResource (CreateSubscriptionResource resource) {

        return new CreateSubscriptionCommand (
              resource.price(),
                resource.description(),
                resource.paid(),
                resource.profileId());
    }

}
