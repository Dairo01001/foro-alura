package com.dairodev.api_foro.UserProfile;

import com.dairodev.api_foro.User.User;

import java.util.List;


public interface UserProfileService {
    UserProfile saveUserProfile(UserProfile userProfile);
    List<UserProfile> getUserProfiles(User user);
}
