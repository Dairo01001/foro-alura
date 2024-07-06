package com.dairodev.api_foro.Profile;

import com.dairodev.api_foro.Profile.model.Profile;
import com.dairodev.api_foro.Profile.model.ProfileRepository;
import com.dairodev.api_foro.Profile.model.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImp implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Boolean profileExists(String name) {
        return profileRepository.existsByName(name);
    }

    @Override
    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Profile saveProfile(Profile profile) {
        if (profileExists(profile.getName())) {
            throw new ResponseStatusException((HttpStatus.CONFLICT), "Profile already exists");
        }
        return profileRepository.save(profile);
    }

    @Override
    public Profile getProfileByID(UUID id) {
        return profileRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND), "Profile not found"));
    }
}
