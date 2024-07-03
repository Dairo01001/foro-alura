package com.dairodev.api_foro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicTests {

    @Value("${local.server.port}")
    private int port;

    @Test
    public void givenIAmOnTheTopic_WhenIRegisterATopic_ThenTheTopicIsCreated() {
        var response = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:" + port)
                .build()
                .post()
                .uri("/topics")
                .exchange();

        itShouldCreateANewTopic(response);
        itShouldAllocateANewIdToTheTopic(response);
    }

    private void itShouldCreateANewTopic(ResponseSpec response) {
        response
                .expectStatus()
                .isCreated();
    }

    private void itShouldAllocateANewIdToTheTopic(ResponseSpec response) {
        response
                .expectBody(TopicResponse.class)
                .value(topic -> {
                    assertThat(topic.getId()).isNotEqualTo(new UUID(0, 0));
                });
    }
}
