package com.dairodev.api_foro.User;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<User> saveUser(
            @RequestBody @Valid RegisterUserRequest registerUserRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        User newUser = User.register(
                registerUserRequest.name(),
                registerUserRequest.email(),
                registerUserRequest.password()
        );

        URI newUserLocation = uriComponentsBuilder
                .path("/users/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        userService.saveUser(newUser, registerUserRequest.profiles());

        return ResponseEntity.created(newUserLocation).body(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        UserResponse user = userService.getUserByID(id);
        return ResponseEntity.ok(user);
    }
}
