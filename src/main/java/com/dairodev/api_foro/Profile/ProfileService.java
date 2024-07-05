package com.dairodev.api_foro.Profile;

import java.util.UUID;

public interface ProfileService {
    Profile saveProfile(Profile profile);

    Profile getProfileByID(UUID id);
}