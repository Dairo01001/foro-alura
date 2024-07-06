package com.dairodev.api_foro.Profile.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    boolean existsByName(String name);
}
