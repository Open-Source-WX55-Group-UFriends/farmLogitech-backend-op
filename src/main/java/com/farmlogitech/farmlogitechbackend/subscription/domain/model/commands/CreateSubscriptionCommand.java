package com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands;

public record CreateSubscriptionCommand(Integer price, String description, Boolean paid,Long profileId){


}
