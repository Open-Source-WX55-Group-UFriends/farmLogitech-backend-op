package com.farmlogitech.farmlogitechbackend.subscription.application.internal.services.commandservices;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.UpdateSubscriptionCommand;
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
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {
        var subscription = new Subscription(command.price(),command.description(),command.paid(), command.profileId());
        subscriptionRepository.save(subscription);
        return Optional.of(subscription);

    }

    @Override
    public Optional<Subscription> handle(UpdateSubscriptionCommand command) {
        var subscription = subscriptionRepository.findByProfileId(command.profileId());
        if (!subscription.isPresent()){
            throw new IllegalArgumentException("Subscription doesn't already exists");

        }
        subscription.get().updateToPaid();
        var updatedSubscription = subscriptionRepository.save(subscription.get());
        return Optional.of(updatedSubscription);

    }
}
