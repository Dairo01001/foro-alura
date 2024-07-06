package com.dairodev.api_foro.Auth;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank String email,
        @Min(6) String password
) {
}
