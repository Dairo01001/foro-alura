package com.dairodev.api_foro.Topic;

import com.dairodev.api_foro.Topic.dto.UpdateTopicRequest;
import com.dairodev.api_foro.Topic.model.Topic;
import com.dairodev.api_foro.Topic.model.TopicRepository;
import com.dairodev.api_foro.Topic.model.TopicService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class TopicServiceImp implements TopicService {
    private final TopicRepository topicRepository;

    public TopicServiceImp(TopicRepository topicRepository) {
        super();
        this.topicRepository = topicRepository;
    }

    @Override
    public Topic getTopicByID(UUID id) {
        return topicRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.NOT_FOUND), "Topic not found"));
    }

    @Override
    public boolean topicExists(String title, String message) {
        return topicRepository.existsByTitleAndMessage(title, message);
    }

    @Override
    public  Topic saveTopic(Topic topic) {
        if (topicExists(topic.getTitle(), topic.getMessage())) {
            throw new ResponseStatusException((HttpStatus.CONFLICT), "Topic already exists");
        }
        topicRepository.save(topic);
        return topic;
    }

    @Override
    public Page<Topic> getTopics(Pageable pageable) {
        return topicRepository.findByStatusTrueOrderByCreatedAtAsc(pageable);
    }

    @Override
    public Topic updateTopic(UUID id, UpdateTopicRequest updateTopicRequest) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isEmpty()) {
            throw new ResponseStatusException((HttpStatus.NOT_FOUND), "Topic not found");
        }

        if(topicExists(updateTopicRequest.title(), updateTopicRequest.message())) {
            throw new ResponseStatusException((HttpStatus.CONFLICT), "Topic already exists");
        }

        Topic topic = optionalTopic.get();
        topic.update(updateTopicRequest);

        topicRepository.save(topic);
        return topic;
    }
}