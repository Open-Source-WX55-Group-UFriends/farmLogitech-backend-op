package com.farmlogitech.farmlogitechbackend.subscription.application.internal.services.commandservices;


import com.farmlogitech.farmlogitechbackend.profiles_managment.application.internal.outboundservices.acl.ExternalProfileService;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.domain.services.SubscriptionCommandService;
import com.farmlogitech.farmlogitechbackend.subscription.infrastructure.persistence.jpa.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

    private final SubscriptionRepository subscriptionRepository;
    private final ExternalProfileService externalProfileService;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository, ExternalProfileService externalProfileService)
    {
        this.subscriptionRepository = subscriptionRepository;
        this.externalProfileService = externalProfileService;
    }


    @Override
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {
        var subscription = new Subscription(command.price(),command.description(),command.paid(), command.profileId());
        subscriptionRepository.save(subscription);
        return Optional.of(subscription);

    }
}
