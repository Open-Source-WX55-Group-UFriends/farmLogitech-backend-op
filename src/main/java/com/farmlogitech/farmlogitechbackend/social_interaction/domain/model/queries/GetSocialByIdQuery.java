package com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries;

public record GetSocialByIdQuery(Integer id) {
    public GetSocialByIdQuery {
        if(id == null || id==0){
            throw new IllegalArgumentException("id must be greater than zero");
        }
    }
}
