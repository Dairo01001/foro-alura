package com.dairodev.api_foro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicTests {

    @Value("${local.server.port}")
    private int port;

    private static final String TOPIC_PATH = "/topics";

    private String baseUri() {
        return "http://localhost:" + port;
    }

    private WebTestClient newWebClient() {
        return WebTestClient
                .bindToServer()
                .baseUrl(baseUri())
                .build();
    }

    private ResponseSpec registerTopic(RegisterTopicRequest topicRequest) {
        return newWebClient()
                .post()
                .uri(TOPIC_PATH)
                .bodyValue(topicRequest)
                .exchange();
    }

    @Test
    public void givenIAmOnTheTopic_WhenIRegisterATopic_ThenTheTopicIsCreated() {
        RegisterTopicRequest topicRequest = new RegisterTopicRequest(
                "Topic Title",
                "Topic Message",
                LocalDate.now(),
                true
        );

        var response = registerTopic(topicRequest);

        itShouldCreateANewTopic(response);
        TopicResponse newTopic = itShouldAllocateANewIdToTheTopic(response);
        itShouldShowWhereToLocateNewTopic(response, newTopic);
        itShouldConfirmTopicDetails(topicRequest, newTopic);
    }

    private void itShouldCreateANewTopic(ResponseSpec response) {
        response
                .expectStatus()
                .isCreated();
    }

    private TopicResponse itShouldAllocateANewIdToTheTopic(ResponseSpec response) {
        return response
                .expectBody(TopicResponse.class)
                .value(topic -> {
                    assertThat(topic.getId()).isNotEqualTo(new UUID(0, 0));
                    assertThat(topic.getId()).isNotNull();
                })
                .returnResult()
                .getResponseBody();
    }

    private void itShouldShowWhereToLocateNewTopic(ResponseSpec response, TopicResponse newTopic) {
        response
                .expectHeader()
                .location((baseUri() + TOPIC_PATH + "/" + newTopic.getId()));
    }

    private void itShouldConfirmTopicDetails(RegisterTopicRequest topicRequest, TopicResponse newTopic) {
        assertThat(newTopic.getTitle()).isEqualTo(topicRequest.getTitle());
        assertThat(newTopic.getMessage()).isEqualTo(topicRequest.getMessage());
        assertThat(newTopic.getCreatedAt()).isEqualTo(topicRequest.getCreatedAt());
        assertThat(newTopic.getStatus()).isEqualTo(topicRequest.getStatus());
    }

    @Test
    public void givenIhaveCreatedATopic_WhenIRequestTheTopic_ThenTheTopicIsReturned() {
        RegisterTopicRequest topicRequest = new RegisterTopicRequest(
                "Topic Title",
                "Topic Message",
                LocalDate.now(),
                true
        );

        URI newTopicLocation = registerTopic(topicRequest)
                .expectBody(TopicResponse.class)
                .returnResult()
                .getResponseHeaders().getLocation();

        ResponseSpec response = newWebClient()
                .get()
                .uri(newTopicLocation)
                .exchange();

        itShouldFindTheNewTopic(response);
    }

    private void itShouldFindTheNewTopic(ResponseSpec response) {
        response
                .expectStatus()
                .isOk();
    }
}
