package com.dairodev.api_foro.Topic.controller;

import com.dairodev.api_foro.Topic.domain.RegisterTopicRequest;
import com.dairodev.api_foro.Topic.domain.Topic;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @PostMapping
    ResponseEntity<Topic> createTopic(
            @RequestBody @Valid RegisterTopicRequest registerTopicRequest,
            UriComponentsBuilder uriBuilder
    ) {
        Topic newTopic = Topic.register(registerTopicRequest);

        URI newTopicLocation = uriBuilder
                .path("/topics/{id}")
                .buildAndExpand(newTopic.getId())
                .toUri();

        return ResponseEntity.created(newTopicLocation).body(newTopic);
    }

    @GetMapping("/{id}")
    void getTopic() {
    }
}
