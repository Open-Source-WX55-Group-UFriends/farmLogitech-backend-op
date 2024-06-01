package com.farmlogitech.farmlogitechbackend.social_interaction.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates.Social;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries.GetAllSocialQuery;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries.GetAllSocialsByFarmIdQuery;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.services.SocialQueryService;
import com.farmlogitech.farmlogitechbackend.social_interaction.infrastructure.persistence.jpa.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialQueryServiceImpl implements SocialQueryService {
    private final SocialRepository socialRepository;

    public SocialQueryServiceImpl(SocialRepository socialRepository)
    {
        this.socialRepository = socialRepository;
    }


    @Override
    public List<Social> handle(GetAllSocialQuery query) {
        return List.of();
    }

    @Override
    public List<Social> handle(GetAllSocialsByFarmIdQuery query) {
    return socialRepository.findAllByFarmId(query.farmId().longValue());
    }

}
