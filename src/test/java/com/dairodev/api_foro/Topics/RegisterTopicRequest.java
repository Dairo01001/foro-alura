package com.dairodev.api_foro.Topics;

import java.time.LocalDate;

public class RegisterTopicRequest {
    private String title;
    private String message;
    private LocalDate createdAt;
    private Boolean status;

    public RegisterTopicRequest(String title, String message, LocalDate createdAt, Boolean status) {
        this.title = title;
        this.message = message;
        this.createdAt = createdAt;
        this.status = status;
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
