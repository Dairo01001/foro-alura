package com.dairodev.api_foro.Profile;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    @Transactional
    ResponseEntity<Profile> createTopic(
            @RequestBody @Valid RegisterProfileRequest registerProfileRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Profile newProfile = Profile.register(registerProfileRequest.name());

        profileService.saveProfile(newProfile);
        URI newTopicLocation = uriComponentsBuilder
                .path("/profiles/{id}")
                .buildAndExpand(newProfile.getId())
                .toUri();

        return ResponseEntity.created(newTopicLocation).body(newProfile);
    }

    @GetMapping("/{id}")
    ResponseEntity<Profile> getProfile(@PathVariable UUID id) {
        Profile profile = profileService.getProfileByID(id);
        return ResponseEntity.ok(profile);
    }
}
