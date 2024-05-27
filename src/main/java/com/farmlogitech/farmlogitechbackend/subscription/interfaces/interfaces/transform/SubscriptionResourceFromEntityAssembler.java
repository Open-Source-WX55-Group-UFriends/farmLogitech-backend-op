package com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.transform;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.resources.SubscriptionResource;

public class SubscriptionResourceFromEntityAssembler {
    public static SubscriptionResource toResourceFromEntity(Subscription entity) {
        return new SubscriptionResource(entity.getId(), entity.getPrice(), entity.getDescription(), entity.getPaid());
    }
}
