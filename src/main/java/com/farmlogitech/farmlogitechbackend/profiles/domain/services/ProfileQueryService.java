package com.farmlogitech.farmlogitechbackend.profiles.domain.services;


import com.farmlogitech.farmlogitechbackend.profiles.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.queries.GetAllProfilesQuery;
import com.farmlogitech.farmlogitechbackend.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
}
