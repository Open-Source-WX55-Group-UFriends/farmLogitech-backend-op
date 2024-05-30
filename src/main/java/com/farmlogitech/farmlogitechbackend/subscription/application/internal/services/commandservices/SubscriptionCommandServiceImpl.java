package com.farmlogitech.farmlogitechbackend.subscription.application.internal.services.commandservices;


import com.farmlogitech.farmlogitechbackend.subscription.application.internal.outboundservices.acl.ExternalProfileService;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.UpdateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.valueobjects.ProfileId;
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
    public Optional<Subscription> handle(CreateSubscriptionCommand command){

        var profileId = externalProfileService.createProfile(command.firstName(),command.lastName(),command.direction(),command.phone(),command.gender(),command.birthDate(),command.documentNumber(),command.documentType(),command.role());

        var newSubscription = new Subscription(profileId.get(),command.price(), command.description(), command.paid());
        var createdSubscription = subscriptionRepository.save(newSubscription);
        return Optional.of(createdSubscription);
    }

    @Override
    public Optional<Subscription> handle(UpdateSubscriptionCommand command)
    {
        if (subscriptionRepository.existsById(command.profileId().profileId().intValue()))
        {
            var subscription = subscriptionRepository.findById(command.profileId().profileId().intValue()).get();
            subscription.updateInformation(command);
            var updateSubscription = subscriptionRepository.save(subscription);
            return Optional.of(updateSubscription);
        }
        throw new IllegalArgumentException("Subscription doesn't alredy exists");
    }
}
