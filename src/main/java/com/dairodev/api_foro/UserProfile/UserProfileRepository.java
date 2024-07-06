package com.dairodev.api_foro.UserProfile;

import com.dairodev.api_foro.Profile.model.Profile;
import com.dairodev.api_foro.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
    List<UserProfile> findAllByUser(User user);

    Boolean existsByUserAndProfile(User user, Profile profile);
}