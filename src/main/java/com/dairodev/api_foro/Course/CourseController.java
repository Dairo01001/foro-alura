package com.dairodev.api_foro.Course;

import com.dairodev.api_foro.Course.dto.RegisterCourseRequest;
import com.dairodev.api_foro.Course.dto.UpdateCourseRequest;
import com.dairodev.api_foro.Course.model.Course;
import com.dairodev.api_foro.Course.model.CourseService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @Transactional
    public ResponseEntity<Course> createCourse(
            @RequestBody @Valid RegisterCourseRequest registerCourseRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Course newCourse = Course.register(registerCourseRequest.name(), registerCourseRequest.category());

        courseService.saveCourse(newCourse);

        URI newCourseLocation = uriComponentsBuilder
                .path("/courses/{id}")
                .buildAndExpand(newCourse.getId())
                .toUri();

        return ResponseEntity.created(newCourseLocation).body(newCourse);
    }

    @GetMapping("/{id}")
    ResponseEntity<Course> getCourse(@PathVariable UUID id) {
        Course course = courseService.getCourseByID(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    ResponseEntity<List<Course>> getCourses() {
        return ResponseEntity.ok(courseService.getCourses());
    }

    @PutMapping("/{id}")
    @Transactional
    ResponseEntity<Course> updateCourse(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateCourseRequest updateCourseRequest
    ) {
        return ResponseEntity.ok(courseService.updateCourse(id, updateCourseRequest));
    }
}
