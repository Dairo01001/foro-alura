package com.dairodev.api_foro.Topic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterTopicRequest(
        @NotBlank String title,
        @NotBlank String message,
        @NotNull LocalDate createdAt,
        @NotNull Boolean status,
        @NotNull UUID courseId,
        @NotNull UUID authorId
) {
}
