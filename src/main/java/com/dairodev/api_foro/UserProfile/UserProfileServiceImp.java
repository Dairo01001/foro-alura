package com.dairodev.api_foro.UserProfile;

import com.dairodev.api_foro.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImp implements UserProfileService {

    private final UserProfileRepository userProfileRepository;


    @Override
    public UserProfile saveUserProfile(UserProfile userProfile) {
        if (userProfileRepository.existsByUserAndProfile(userProfile.getUser(), userProfile.getProfile())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This profile already exists in this user");
        }
        return userProfileRepository.save(userProfile);
    }

    @Override
    public List<UserProfile> getUserProfiles(User user) {
        return userProfileRepository.findAllByUser(user);
    }
}
