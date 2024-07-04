package com.dairodev.api_foro.Topic.model;

import com.dairodev.api_foro.Topic.dto.UpdateTopicRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TopicService {
    Topic getTopicByID(UUID id);

    boolean topicExists(String title, String message);

    Topic saveTopic(Topic topic);

    Page<Topic> getTopics(Pageable pageable);

    Topic updateTopic(UUID id, UpdateTopicRequest updateTopicRequest);
}
