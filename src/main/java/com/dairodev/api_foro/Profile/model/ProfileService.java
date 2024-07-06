package com.dairodev.api_foro.Profile.model;

import java.util.List;
import java.util.UUID;

public interface ProfileService {
    Profile saveProfile(Profile profile);

    Profile getProfileByID(UUID id);

    Boolean profileExists(String name);

    List<Profile> getProfiles();
}