package com.farmlogitech.farmlogitechbackend.social_interaction.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.aggregates.Social;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries.GetAllSocialQuery;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.model.queries.GetAllSocialsByFarmIdQuery;
import com.farmlogitech.farmlogitechbackend.social_interaction.domain.services.SocialQueryService;
import com.farmlogitech.farmlogitechbackend.social_interaction.infrastructure.persistence.jpa.SocialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Map<Integer, Long> handle(GetAllSocialsByFarmIdQuery query) {
        // Obtén todas las interacciones sociales para la granja dada
        List<Social> allSocials = socialRepository.findAllByFarmId(query.farmId().longValue());

        // Agrupa las interacciones sociales por valoración y cuenta cuántas hay de cada una
        Map<Integer, Long> ratingsCount = allSocials.stream()
                .collect(Collectors.groupingBy(Social::getRating, Collectors.counting()));

        return ratingsCount;
    }
}