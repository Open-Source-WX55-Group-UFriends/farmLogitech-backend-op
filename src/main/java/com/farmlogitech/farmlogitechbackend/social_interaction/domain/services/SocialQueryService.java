package com.farmlogitech.farmlogitechbackend.social_interaction.domain.services;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates.Social;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries.GetAllSocialQuery;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries.GetAllSocialsByFarmIdQuery;

import java.util.List;

public interface SocialQueryService {
    List<Social> handle(GetAllSocialQuery query);
    List<Social> handle(GetAllSocialsByFarmIdQuery query);
}
