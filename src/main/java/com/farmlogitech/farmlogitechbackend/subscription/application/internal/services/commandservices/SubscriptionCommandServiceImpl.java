package com.farmlogitech.farmlogitechbackend.subscription.application.internal.services.commandservices;


import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.domain.services.SubscriptionCommandService;
import com.farmlogitech.farmlogitechbackend.subscription.infrastructure.persistence.jpa.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository)
    {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Optional<Subscription> handle(CreateSubscriptionCommand command){
        if(subscriptionRepository.existsById(command.id()))
            throw new IllegalArgumentException("Subscription alredy exists");
        var newSubscription = new Subscription(command);
        var createdSubscription = subscriptionRepository.save(newSubscription);
        return Optional.of(createdSubscription);
    }
}
