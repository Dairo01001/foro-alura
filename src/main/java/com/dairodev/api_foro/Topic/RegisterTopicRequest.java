package com.dairodev.api_foro.Topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterTopicRequest(
        @NotBlank String title,
        @NotBlank String message,
        @NotNull LocalDate createdAt,
        @NotNull Boolean status
) {
}
