package com.farmlogitech.farmlogitechbackend.profiles_managment.application.internal.outboundservices.acl;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.valueobjects.SubscriptionId;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.interfaces.acl.SubscriptionContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class ExternalSubscriptionService {
    private final SubscriptionContextFacade subscriptionContextFacade;

    public ExternalSubscriptionService(SubscriptionContextFacade subscriptionContextFacade) {
        this.subscriptionContextFacade = subscriptionContextFacade;
    }

    public Optional<SubscriptionId>createSubscription(Integer price, String description, Boolean paid, Long profileId) {
        var subscriptionId = subscriptionContextFacade.createSubscription(price, description, paid, profileId);
        if (subscriptionId == 0L) return Optional.empty();
        return Optional.of(new SubscriptionId(subscriptionId));
    }

}
