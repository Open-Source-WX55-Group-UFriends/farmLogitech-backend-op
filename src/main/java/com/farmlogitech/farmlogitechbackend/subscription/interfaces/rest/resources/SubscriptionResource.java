package com.farmlogitech.farmlogitechbackend.subscription.interfaces.rest.resources;

public record SubscriptionResource(Integer price, String description, Boolean paid,Long profileId) {
}
