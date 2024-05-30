package com.farmlogitech.farmlogitechbackend.profiles_managment.application.internal.services.queryservices;

import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.Profile;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetAllProfilesQuery;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetAllUsersQuery;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetProfileById;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetUserByIdQuery;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services.ProfileManagmentQueryService;
import com.farmlogitech.farmlogitechbackend.profiles_managment.infrastructure.persistence.jpa.ProfileRepository;
import com.farmlogitech.farmlogitechbackend.profiles_managment.infrastructure.persistence.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileManagmentQueryServiceImpl  implements ProfileManagmentQueryService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public ProfileManagmentQueryServiceImpl(UserRepository userRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {

        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> handle(GetProfileById query) {

        return profileRepository.findById(query.id());
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.Id().intValue());
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }


}
