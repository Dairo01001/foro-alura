package com.dairodev.api_foro.User;

import com.dairodev.api_foro.Profile.model.Profile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record RegisterUserRequest(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank @Min(6) String password,
        @NotNull Set<Profile> profiles
) {
}
