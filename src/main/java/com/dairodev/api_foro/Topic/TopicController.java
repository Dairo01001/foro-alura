package com.dairodev.api_foro.Topic;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        super();
        this.topicService = topicService;
    }


    @PostMapping
    ResponseEntity<Topic> createTopic(
            @RequestBody @Valid RegisterTopicRequest registerTopicRequest
    ) {
        Topic newTopic = Topic.register(registerTopicRequest);

        topicService.saveTopic(newTopic);

        URI newTopicLocation = topicUri(newTopic.getId());
        return ResponseEntity.created(newTopicLocation).body(newTopic);
    }

    private URI topicUri(UUID id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    @GetMapping("/{id}")
    Topic getTopic(@PathVariable UUID id) {
        Topic topic = topicService.getTopicByID(id);
        return topic;
    }
}
