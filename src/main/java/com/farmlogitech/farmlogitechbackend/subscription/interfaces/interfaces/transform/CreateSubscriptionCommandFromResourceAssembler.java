package com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.transform;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.resources.CreateSubscriptionResource;

public class CreateSubscriptionCommandFromResourceAssembler {
    public static CreateSubscriptionCommand toCommandFromResource (CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand (resource.id(), resource.price(), resource.description(),
                resource.paid());
    }

}
