package com.farmlogitech.farmlogitechbackend.subscription.domain.services;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.domain.model.queries.GetAllSubscriptionQuery;

import java.util.List;

public interface SubscriptionQueryService {
    List<Subscription> handle(GetAllSubscriptionQuery query);
}
