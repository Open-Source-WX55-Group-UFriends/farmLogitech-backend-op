package com.farmlogitech.farmlogitechbackend.subscription.interfaces.rest.transform;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.rest.resources.SubscriptionResource;

public class SubscriptionResourceFromEntityAssembler {
    public static SubscriptionResource toResourceFromEntity(Subscription entity) {
        return new SubscriptionResource(entity.getPrice(), entity.getDescription(), entity.getPaid(), entity.getProfileId());
    }
}
