package com.farmlogitech.farmlogitechbackend.repository;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.aggregates.Subscription;
import com.farmlogitech.farmlogitechbackend.subscription.infrastructure.persistence.jpa.SubscriptionRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class SubscriptionRepositoryTests {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Test
    public void Subscription_SaveAll_ReturnSavedSubscription(){
        //Arrange
        Subscription subscription = Subscription.builder()
                .price(100)
                .description("description")
                .paid(true)
                .profileId(1L)
                .build();

        //Act
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        //Assert
        Assertions.assertThat(savedSubscription).isNotNull();
        Assertions.assertThat(savedSubscription.getPaid()).isTrue();

    }
}
