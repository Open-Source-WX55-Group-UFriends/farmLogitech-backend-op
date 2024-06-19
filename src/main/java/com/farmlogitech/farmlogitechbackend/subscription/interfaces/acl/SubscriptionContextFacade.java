package com.farmlogitech.farmlogitechbackend.subscription.interfaces.acl;

import com.farmlogitech.farmlogitechbackend.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.farmlogitech.farmlogitechbackend.subscription.domain.services.SubscriptionCommandService;
import org.springframework.stereotype.Service;

@Service

public class SubscriptionContextFacade {
    private final SubscriptionCommandService subscriptionCommandService;


    public SubscriptionContextFacade(SubscriptionCommandService subscriptionCommandService) {
        this.subscriptionCommandService = subscriptionCommandService;
    }

    /*
    *
    *   public Long createProfile(String firstName, String lastName, String direction, String phone, String gender, String birthDate,
    * String documentNumber, String documentType, String role) {
       var createProfileCommand = new CreateProfileCommnad( firstName,  lastName,  direction,  phone,  gender,  birthDate,  documentNumber,
       *   documentType, role);
       var profile= profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }

    * */

    public Long createSubscription(Integer price, String description, Boolean paid) {

        var createSubscriptionCommand = new CreateSubscriptionCommand(price, description, paid);
        var subscription = subscriptionCommandService.handle(createSubscriptionCommand);
        if (subscription.isEmpty()) return 0L;
        return subscription.get().getId();
    }

}
