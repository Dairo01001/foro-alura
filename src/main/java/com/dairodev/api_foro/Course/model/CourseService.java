package com.dairodev.api_foro.Course.model;

import com.dairodev.api_foro.Course.dto.UpdateCourseRequest;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    Course getCourseByID(UUID id);

    Course saveCourse(Course course);

    List<Course> getCourses();

    Course updateCourse(UUID id, UpdateCourseRequest updateCourseRequest);
}
