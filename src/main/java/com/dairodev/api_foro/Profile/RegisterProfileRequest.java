package com.dairodev.api_foro.Profile;

import jakarta.validation.constraints.NotBlank;

public record RegisterProfileRequest(
       @NotBlank String name
) {
}