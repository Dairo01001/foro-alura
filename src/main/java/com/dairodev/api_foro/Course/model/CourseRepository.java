package com.dairodev.api_foro.Course.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    Boolean existsByName(String name);
}
