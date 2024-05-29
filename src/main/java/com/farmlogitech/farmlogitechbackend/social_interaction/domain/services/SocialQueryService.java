package com.farmlogitech.farmlogitechbackend.social_interaction.domain.services;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates.Social;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries.GetAllSocialQuery;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries.GetSocialByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SocialQueryService {
    List<Social> handle(GetAllSocialQuery query);

    Optional<Social> handle(GetSocialByIdQuery query);
}
