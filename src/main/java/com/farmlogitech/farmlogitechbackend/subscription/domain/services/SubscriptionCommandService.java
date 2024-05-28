package com.farmlogitech.farmlogitechbackend.subscription.domain.services;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.CreateSubscriptionCommand;

import java.util.Optional;

public interface SubscriptionCommandService {
    Optional<Subscription> handle(CreateSubscriptionCommand command);
}
