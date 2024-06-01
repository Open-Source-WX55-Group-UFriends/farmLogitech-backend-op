package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.aggregates.User;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.model.queries.GetAllUsersQuery;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services.ProfileManagementCommandService;
import com.farmlogitech.farmlogitechbackend.profiles_managment.domain.services.ProfileManagmentQueryService;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources.CreateUserResource;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.resources.UserResource;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final ProfileManagmentQueryService userQueryService;
    private final ProfileManagementCommandService userCommandService;

    public UserController(ProfileManagmentQueryService userQueryService, ProfileManagementCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        Optional<User> user = userCommandService.handle(CreateUserCommandFromResourceAssembler.toCommandFromResource(resource));
        return user.map(resp ->
                        new ResponseEntity<>(UserResourceFromEntityAssembler
                                .toResourceFromEntity(resp), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var users = userQueryService.handle(new GetAllUsersQuery());
        if(users.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var userResources = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }


}
