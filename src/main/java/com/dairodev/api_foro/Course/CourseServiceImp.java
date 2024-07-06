package com.dairodev.api_foro.Course;

import com.dairodev.api_foro.Course.dto.UpdateCourseRequest;
import com.dairodev.api_foro.Course.model.Course;
import com.dairodev.api_foro.Course.model.CourseRepository;
import com.dairodev.api_foro.Course.model.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImp implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course getCourseByID(UUID id) {
        return courseRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
    }

    @Override
    public Course saveCourse(Course course) {
        if (courseRepository.existsByName(course.getName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course already exists");
        }
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(UUID id, UpdateCourseRequest updateCourseRequest) {
        Course course = getCourseByID(id);

        if (courseRepository.existsByName(updateCourseRequest.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course already exists");
        }

        course.update(updateCourseRequest.name(), updateCourseRequest.category());

        return courseRepository.save(course);
    }
}
