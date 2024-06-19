package com.farmlogitech.farmlogitechbackend.subscription.application.internal.services.commandservices;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.UpdateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.domain.services.SubscriptionCommandService;
import com.farmlogitech.farmlogitechbackend.subscription.infrastructure.persistence.jpa.SubscriptionRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        boolean hasRequiredRole = authorities.stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_FARMER"));

        if (hasRequiredRole) {
            // Check if a subscription already exists for this user
            Optional<Subscription> existingSubscription = subscriptionRepository.findByProfileId(userDetails.getId());
            if (existingSubscription.isPresent()) {
                throw new IllegalStateException("A subscription already exists for this user");
            }

            var subscription = new Subscription(command.price(), command.description(), command.paid(), userDetails.getId());
            subscription.setPaid(true);
            subscriptionRepository.save(subscription);
            return Optional.of(subscription);
        } else {
            throw new SecurityException(" Only farmers can create a subscription");
        }
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
