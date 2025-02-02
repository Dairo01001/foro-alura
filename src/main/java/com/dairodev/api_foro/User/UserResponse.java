package com.dairodev.api_foro.User;

import java.util.List;

public record UserResponse(
        String name,
        String email,
        List<String> profiles
) {
    public static UserResponse fromUser(User author) {
        return new UserResponse(
                author.getName(),
                author.getEmail(),
                List.of()
        );
    }
}
