package com.farmlogitech.farmlogitechbackend.subscription.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    List<Subscription> findAllSubscription();
}
