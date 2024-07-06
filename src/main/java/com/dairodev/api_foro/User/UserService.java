package com.dairodev.api_foro.User;

import com.dairodev.api_foro.Auth.AppUser;
import com.dairodev.api_foro.Profile.model.Profile;

import java.util.Set;
import java.util.UUID;

public interface UserService {
    User saveUser(User user, Set<Profile> profiles);

    UserResponse getUserByID(UUID id);

    User getReferenceByID(UUID uuid);

    AppUser getUserByEmail(String email);
}
