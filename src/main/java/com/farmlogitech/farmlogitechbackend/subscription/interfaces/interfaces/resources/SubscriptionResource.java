package com.farmlogitech.farmlogitechbackend.subscription.interfaces.interfaces.resources;

public record SubscriptionResource(Long profileId, Integer price, String description, Boolean paid) {
}
