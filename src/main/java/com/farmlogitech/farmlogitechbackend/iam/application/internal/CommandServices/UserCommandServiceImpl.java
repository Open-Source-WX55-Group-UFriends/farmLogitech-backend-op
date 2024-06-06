package com.farmlogitech.farmlogitechbackend.iam.application.internal.CommandServices;

import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.hashing.HashingService;
import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.tokens.TokeService;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.commands.SignInCommand;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.commands.SignUpCommand;
import com.farmlogitech.farmlogitechbackend.iam.domain.services.UserCommandService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.jpa.repositories.RoleRepository;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class UserCommandServiceImpl implements UserCommandService{
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokeService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hasingService, TokeService tokeService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hasingService;
        this.tokenService = tokeService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if(userRepository.existByUsername(command.username())){
            throw new RuntimeException("username already exist");

        }
        var roles= command.roles().stream()
                .map(role -> roleRepository.findByName(role.getName())
                        .orElseThrow(()->new RuntimeException("Role not found"))).toList();
    var user= new User(command.username(), hashingService.encode(command.password()), roles);
    userRepository.save(user);
    return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}
