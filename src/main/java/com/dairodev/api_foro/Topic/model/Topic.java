package com.dairodev.api_foro.Topic.model;

import com.dairodev.api_foro.Answer.Answer;
import com.dairodev.api_foro.Course.Course;
import com.dairodev.api_foro.Topic.dto.RegisterTopicRequest;
import com.dairodev.api_foro.Topic.dto.UpdateTopicRequest;
import com.dairodev.api_foro.User.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "Topic")
@Table(name = "topics")
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

    @OneToMany(mappedBy = "topic")
    private List<Answer> answers;

    public Topic() {
    }

    public Topic(UUID id, String title, String message, LocalDate createdAt, Boolean status) {
        super();
        setId(id);
        setTitle(title);
        setMessage(message);
        setCreatedAt(createdAt);
        setStatus(status);
    }

    public static Topic register(String title, String message, LocalDate createdAt, Boolean status) {
        return new Topic(UUID.randomUUID(), title, message, createdAt, status);
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
