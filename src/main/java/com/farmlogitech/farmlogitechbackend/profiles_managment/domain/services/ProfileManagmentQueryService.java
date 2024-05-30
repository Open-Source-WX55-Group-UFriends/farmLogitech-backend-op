package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetAllProfilesQuery;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetProfileById;

import java.util.List;
import java.util.Optional;

public interface ProfileManagmentQueryService {
    List<Profile> handle(GetAllProfilesQuery query);
    Optional<Profile> handle(GetProfileById query);

}
