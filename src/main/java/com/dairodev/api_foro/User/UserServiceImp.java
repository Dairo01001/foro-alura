package com.dairodev.api_foro.User;

import com.dairodev.api_foro.Profile.model.Profile;
import com.dairodev.api_foro.UserProfile.UserProfile;
import com.dairodev.api_foro.UserProfile.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserProfileService userProfileService;

    @Override
    public User saveUser(User user, Set<Profile> profiles) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        userRepository.save(user);

        for (Profile profile : profiles) {
            userProfileService.saveUserProfile(UserProfile.register(user, profile));
        }

        return user;
    }

    @Override
    public UserResponse getUserByID(UUID id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));


        return new UserResponse(
                user.getName(),
                user.getEmail(),
                userProfileService.getUserProfiles(user).stream()
                        .map(UserProfile::getProfile)
                        .map(Profile::getName)
                        .toList()
        );
    }
}
