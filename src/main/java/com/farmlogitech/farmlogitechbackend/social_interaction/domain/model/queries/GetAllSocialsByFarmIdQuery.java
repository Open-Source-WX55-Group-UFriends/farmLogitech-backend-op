package com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries;

public record GetAllSocialsByFarmIdQuery(Long farmId) {
    public GetAllSocialsByFarmIdQuery {
        if(farmId == null || farmId==0){
            throw new IllegalArgumentException("id must be greater than zero");
        }
    }
}
