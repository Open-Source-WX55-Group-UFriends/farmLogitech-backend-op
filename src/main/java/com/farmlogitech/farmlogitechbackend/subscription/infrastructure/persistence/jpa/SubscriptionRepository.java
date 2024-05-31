package com.farmlogitech.farmlogitechbackend.subscription.infrastructure.persistence.jpa;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.valueobjects.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
 /*
    Optional<Subscription>findByProfileId(ProfileId profileId);

    @Query("SELECT s FROM Subscription s")
    List<Subscription> findAllSubscription();
    */
}
