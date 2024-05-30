package com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.transform;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.resources.CreateSubscriptionResource;

public class CreateSubscriptionCommandFromResourceAssembler {
    public static CreateSubscriptionCommand toCommandFromResource (CreateSubscriptionResource resource) {

        return new CreateSubscriptionCommand (resource.firstName(),
                resource.lastName(), resource.direction(), resource.phone(), resource.gender(), resource.birthDate(), resource.documentNumber(), resource.documentType(), resource.role(), resource.price(),
                resource.description(),
                resource.paid());
    }

}
