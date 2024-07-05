package com.dairodev.api_foro.Course;

import com.dairodev.api_foro.Topic.model.Topic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

@Entity(name = "Course")
@Table(name = "courses")
public class Course {
    private @Id UUID id;
    private String name;
    private String category;

    @OneToMany(mappedBy = "course")
    private List<Topic> topics;
}
