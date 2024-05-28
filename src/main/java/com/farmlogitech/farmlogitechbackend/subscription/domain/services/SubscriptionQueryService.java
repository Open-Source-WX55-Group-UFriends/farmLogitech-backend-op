package com.farmlogitech.farmlogitechbackend.subscription.domain.services;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.queries.GetAllSubscriptionQuery;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.queries.GetSubscriptionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SubscriptionQueryService {
    List<Subscription> handle(GetAllSubscriptionQuery query);

    Optional<Subscription> handle(GetSubscriptionByIdQuery query);
}
