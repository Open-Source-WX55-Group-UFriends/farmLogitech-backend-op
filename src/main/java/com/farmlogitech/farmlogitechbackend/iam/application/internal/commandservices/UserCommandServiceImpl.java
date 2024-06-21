package com.farmlogitech.farmlogitechbackend.iam.application.internal.commandservices;


import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.hashing.HashingService;
import com.farmlogitech.farmlogitechbackend.iam.application.internal.outboundservices.tokens.TokenService;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.commands.SignInCommand;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.commands.SignUpCommand;
import com.farmlogitech.farmlogitechbackend.iam.domain.model.valueobjects.Roles;
import com.farmlogitech.farmlogitechbackend.iam.domain.services.UserCommandService;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.farmlogitech.farmlogitechbackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            return Optional.empty(); // Devuelve un Optional vacío si el nombre de usuario ya existe

        var roles = command.roles();
        if (roles.isEmpty()) {
            var role = roleRepository.findByName(Roles.ROLE_FARMER);
            if (role.isPresent()) roles.add(role.get());
        } else {
            roles = roles.stream().map(role -> {
                var existingRole = roleRepository.findByName(role.getName());
                return existingRole.orElseGet(() -> roleRepository.save(role)); // Crea el rol si no existe
            }).toList();
        }

        var user = new User(command.username(), hashingService.encode(command.password()), roles);
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
