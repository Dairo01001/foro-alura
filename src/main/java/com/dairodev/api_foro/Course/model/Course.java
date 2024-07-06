package com.dairodev.api_foro.Course.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity(name = "Course")
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Course {
    private @Id UUID id;
    private String name;
    private String category;

    public static Course register(String name, String category) {
        return new Course(UUID.randomUUID(), name, category);
    }

    public void update(String name, String category) {
        if (name != null)  setName(name);
        if (category != null)  setCategory(category);
    }
}
