package com.dairodev.api_foro.Topic.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateTopicRequest(
        @NotBlank String title,
        @NotBlank String message,
        Boolean status
) {
}
