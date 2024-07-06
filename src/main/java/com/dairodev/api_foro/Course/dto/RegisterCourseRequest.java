package com.dairodev.api_foro.Course.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterCourseRequest(
        @NotBlank String name,
        @NotBlank String category
) {
}
