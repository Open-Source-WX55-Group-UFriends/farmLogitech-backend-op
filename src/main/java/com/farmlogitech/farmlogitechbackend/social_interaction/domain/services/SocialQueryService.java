package com.farmlogitech.farmlogitechbackend.social_interaction.domain.services;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates.Social;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries.GetAllSocialQuery;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries.GetAllSocialsByFarmIdQuery;

import java.util.List;
import java.util.Map;

public interface SocialQueryService {
    List<Social> handle(GetAllSocialQuery query);
    Map<Integer, Long> handle(GetAllSocialsByFarmIdQuery query);

}