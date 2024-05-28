package com.farmlogitech.farmlogitechbackend.subscription.domain.model.queries;

public record GetSubscriptionByIdQuery(Integer id) {
    public GetSubscriptionByIdQuery {
        if(id==null || id==0){
            throw new IllegalArgumentException("id must be greater than zero");
        }
    }
}
