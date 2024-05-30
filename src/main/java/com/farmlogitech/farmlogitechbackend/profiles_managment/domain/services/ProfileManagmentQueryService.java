package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetAllProfilesQuery;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetAllUsersQuery;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetProfileById;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileManagmentQueryService {
    List<Profile> handle(GetAllProfilesQuery query);
    Optional<Profile> handle(GetProfileById query);
    Optional<User>handle(GetUserByIdQuery query);
    List<User>handle(GetAllUsersQuery query);

}
