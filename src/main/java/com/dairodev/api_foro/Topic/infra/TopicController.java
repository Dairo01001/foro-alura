package com.dairodev.api_foro.Topic.infra;

import com.dairodev.api_foro.Topic.model.Topic;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @PostMapping
    ResponseEntity<Topic> createTopic() {
        Topic newTopic = Topic.create();
        return ResponseEntity.created(null).body(newTopic);
    }
}
