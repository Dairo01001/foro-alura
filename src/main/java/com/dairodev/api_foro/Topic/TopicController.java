package com.dairodev.api_foro.Topic;

import com.dairodev.api_foro.Course.model.Course;
import com.dairodev.api_foro.Course.model.CourseService;
import com.dairodev.api_foro.Topic.dto.RegisterTopicRequest;
import com.dairodev.api_foro.Topic.dto.UpdateTopicRequest;
import com.dairodev.api_foro.Topic.model.Topic;
import com.dairodev.api_foro.Topic.model.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    @Transactional
    ResponseEntity<Topic> createTopic(
            @RequestBody @Valid RegisterTopicRequest registerTopicRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Topic newTopic = Topic.register(registerTopicRequest);

        topicService.saveTopic(newTopic);
        URI newTopicLocation = uriComponentsBuilder
                .path("/topics/{id}")
                .buildAndExpand(newTopic.getId())
                .toUri();

        return ResponseEntity.created(newTopicLocation).body(newTopic);
    }

    @GetMapping
    ResponseEntity<Page<Topic>> getTopics(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        return ResponseEntity.ok(topicService.getTopics(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    ResponseEntity<Topic> getTopic(@PathVariable UUID id) {
        Topic topic = topicService.getTopicByID(id);
        return ResponseEntity.ok(topic);
    }

    @PutMapping("/{id}")
    @Transactional
    ResponseEntity<Topic> updateTopic(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateTopicRequest updateTopicRequest
    ) {
        return ResponseEntity.ok(topicService.updateTopic(id, updateTopicRequest));
    }
}
