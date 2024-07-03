package com.dairodev.api_foro.Topic;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicRepository topicRepository;

    public TopicController(TopicRepository topicRepository) {
        super();
        this.topicRepository = topicRepository;
    }

    @PostMapping
    ResponseEntity<Topic> createTopic(
            @RequestBody @Valid RegisterTopicRequest registerTopicRequest,
            UriComponentsBuilder uriBuilder
    ) {
        Topic newTopic = Topic.register(registerTopicRequest);

        topicRepository.save(newTopic);

        URI newTopicLocation = uriBuilder
                .path("/topics/{id}")
                .buildAndExpand(newTopic.getId())
                .toUri();

        return ResponseEntity.created(newTopicLocation).body(newTopic);
    }

    @GetMapping("/{id}")
    Topic getTopic(@PathVariable UUID id) {
        Topic topic = topicRepository.findById(id).orElseThrow();
        return topic;
    }
}
