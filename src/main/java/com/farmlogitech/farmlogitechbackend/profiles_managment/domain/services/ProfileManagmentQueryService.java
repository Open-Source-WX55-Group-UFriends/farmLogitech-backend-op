package com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services;

import com.farmlogitech.farmlogitechbackend.farms.domain.model.aggregates.Farm;
import com.farmlogitech.farmlogitechbackend.farms.domain.model.queries.GetAllFarmByLocationQuery;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetAllUsersQuery;

import java.util.List;

public interface ProfileManagmentQueryService {
    List<User> handle(GetAllUsersQuery query);


}
