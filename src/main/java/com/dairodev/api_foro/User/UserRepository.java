package com.dairodev.api_foro.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Boolean existsByEmail(String email);
}
