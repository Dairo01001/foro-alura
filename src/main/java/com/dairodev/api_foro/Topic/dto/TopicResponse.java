package com.dairodev.api_foro.Topic.dto;

import com.dairodev.api_foro.Course.model.Course;
import com.dairodev.api_foro.Topic.model.Topic;

import java.net.URI;

public record TopicResponse(
        String title,
        String message,
        String createdAt,
        UserResponse author,
        Course course
) {

    public static TopicResponse fromTopic(Topic newTopic) {
        return new TopicResponse(
                newTopic.getTitle(),
                newTopic.getMessage(),
                newTopic.getCreatedAt().toString(),
                new UserResponse(newTopic.getAuthor().getName()),
                newTopic.getCourse()
        );
    }
}
