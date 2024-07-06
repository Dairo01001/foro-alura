package com.dairodev.api_foro.Topic.model;

import com.dairodev.api_foro.Answer.Answer;
import com.dairodev.api_foro.Course.model.Course;
import com.dairodev.api_foro.Topic.dto.RegisterTopicRequest;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    public Topic(UUID id, String title, String message, LocalDate createdAt, Boolean status) {
        super();
        setId(id);
        setTitle(title);
        setMessage(message);
        setCreatedAt(createdAt);
        setStatus(status);
    }

    public static Topic register(String title, String message, LocalDate createdAt, Boolean status, Course course, User author) {
        return new Topic(UUID.randomUUID(), title, message, createdAt, status, course, author);
    }

    public static Topic register(RegisterTopicRequest registerTopicRequest) {
        return new Topic(
                UUID.randomUUID(),
                registerTopicRequest.title(),
                registerTopicRequest.message(),
                registerTopicRequest.createdAt(),
                registerTopicRequest.status()
        );
    }

    public void update(UpdateTopicRequest updateTopicRequest) {
        if (updateTopicRequest.title() != null)  setTitle(updateTopicRequest.title());
        if (updateTopicRequest.message() != null)  setMessage(updateTopicRequest.message());
        if (updateTopicRequest.status() != null)  setStatus(updateTopicRequest.status());
    }
}
