package com.farmlogitech.farmlogitechbackend.profiles_managment.interfaces;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    /*
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

  */
}
