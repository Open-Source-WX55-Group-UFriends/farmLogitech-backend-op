package com.farmlogitech.farmlogitechbackend.subscription.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
    Optional<Subscription> findByProfileId(Long Id);

    /*

    @Query("SELECT s FROM Subscription s")
    List<Subscription> findAllSubscription();
    */
}
