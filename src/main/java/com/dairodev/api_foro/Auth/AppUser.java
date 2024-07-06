package com.dairodev.api_foro.Auth;

public record AppUser(
        String name,
        String email,
        String password,
        String[] roles
) {
}
