package com.dairodev.api_foro.User;

import java.util.List;

public record UserResponse(
        String name,
        String email,
        List<String> profiles
) {
}
