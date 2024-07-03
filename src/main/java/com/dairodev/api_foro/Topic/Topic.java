package com.dairodev.api_foro.Topic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Topic")
@Table(name = "topics")
public class Topic {
    private @Id UUID id;
    private String title;
    private String message;
    private LocalDate createdAt;
    private Boolean status;

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
}
