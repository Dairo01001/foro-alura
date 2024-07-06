package com.dairodev.api_foro.Topic.model;


import com.dairodev.api_foro.Course.model.Course;
import com.dairodev.api_foro.Topic.dto.UpdateTopicRequest;
import com.dairodev.api_foro.User.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Topic")
@Table(name = "topics")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Topic {
    private @Id UUID id;
    private String title;
    private String message;
    private LocalDate createdAt;
    private Boolean status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    public static Topic register(String title, String message, Course course, User author) {
        return new Topic(UUID.randomUUID(), title, message, LocalDate.now(), true, course, author);
    }

    public void update(UpdateTopicRequest updateTopicRequest) {
        if (updateTopicRequest.title() != null)  setTitle(updateTopicRequest.title());
        if (updateTopicRequest.message() != null)  setMessage(updateTopicRequest.message());
        if (updateTopicRequest.status() != null)  setStatus(updateTopicRequest.status());
    }
}
