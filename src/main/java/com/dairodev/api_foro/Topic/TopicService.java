package com.dairodev.api_foro.Topic;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        super();
        this.topicRepository = topicRepository;
    }

    public Topic getTopicByID(UUID id) {
        return topicRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND), "Topic not found"));
    }

    public boolean topicExists(Topic topic) {
        return topicRepository.existsByTitleAndMessage(topic.getTitle(), topic.getMessage());
    }

    public  Topic saveTopic(Topic topic) {
        if (topicExists(topic)) {
            throw new ResponseStatusException((HttpStatus.CONFLICT), "Topic already exists");
        }
        topicRepository.save(topic);
        return topic;
    }
}
