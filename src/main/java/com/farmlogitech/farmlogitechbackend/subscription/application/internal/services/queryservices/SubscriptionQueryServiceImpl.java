package com.farmlogitech.farmlogitechbackend.subscription.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.queries.GetAllSubscriptionQuery;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.queries.GetSubscriptionByIdQuery;
import com.farmlogitech.farmlogitechbackend.subscription.domain.services.SubscriptionQueryService;
import com.farmlogitech.farmlogitechbackend.subscription.infrastructure.persistence.jpa.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository)
    {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<Subscription> handle(GetAllSubscriptionQuery query){
        return subscriptionRepository.findAll();
    }

    @Override
    public Optional<Subscription> handle(GetSubscriptionByIdQuery query) {
        return subscriptionRepository.findById(query.id());
    }

}
