package com.farmlogitech.farmlogitechbackend.profiles_managment.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.farms.domain.services.FarmQueryService;
import com.farmlogitech.farmlogitechbackend.farms.infrastructure.persistence.jpa.FarmRepository;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetAllUsersQuery;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services.ProfileManagmentQueryService;
import com.farmlogitech.farmlogitechbackend.profiles_managment.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProfileManagmentQueryServiceImpl  implements ProfileManagmentQueryService {
    private final UserRepository userRepository;

    public ProfileManagmentQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAllUsers();
    }
}
