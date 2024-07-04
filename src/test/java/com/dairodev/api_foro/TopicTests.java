package com.dairodev.api_foro;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TopicTests {

    @Autowired
    private TopicApi topicApi;

    @Test
    public void givenIAmOnTheTopic_WhenIRegisterATopic_ThenTheTopicIsCreated() {
        RegisterTopicRequest topicRequest = new RegisterTopicRequest(
                "Topic Title",
                "Topic Message",
                LocalDate.now(),
                true
        );

        var response = topicApi.registerTopic(topicRequest);

        itShouldCreateANewTopic(response);
        TopicResponse newTopic = topicApi.getTopicFromResponse(response);
        itShouldAllocateANewId(newTopic);
        itShouldShowWhereToLocateNewTopic(response, newTopic);
        itShouldConfirmTopicDetails(topicRequest, newTopic);
    }

    private void itShouldCreateANewTopic(ResponseSpec response) {
        response
                .expectStatus()
                .isCreated();
    }

    private void itShouldAllocateANewId(TopicResponse newTopic) {
        assertThat(newTopic.getId()).isNotEqualTo(new UUID(0, 0));
        assertThat(newTopic.getId()).isNotNull();
    }

    private void itShouldShowWhereToLocateNewTopic(ResponseSpec response, TopicResponse newTopic) {
        response
                .expectHeader()
                .location(topicApi.uriForTopicsId(newTopic.getId()).toString());
    }

    private void itShouldConfirmTopicDetails(RegisterTopicRequest topicRequest, TopicResponse newTopic) {
        assertThat(newTopic.getTitle()).isEqualTo(topicRequest.getTitle());
        assertThat(newTopic.getMessage()).isEqualTo(topicRequest.getMessage());
        assertThat(newTopic.getCreatedAt()).isEqualTo(topicRequest.getCreatedAt());
        assertThat(newTopic.getStatus()).isEqualTo(topicRequest.getStatus());
    }

    @ParameterizedTest
    @CsvSource({"Test Title,Test Message,2020-01-01,true", "Another Test Title,Another Test Message,2020-02-02,false"})
    public void givenIHaveCreatedATopic_WhenIRequestTheTopic_ThenTheTopicIsReturned(
            @AggregateWith(TopicAggregator.class) RegisterTopicRequest topicRequest
    ) {
        URI newTopicLocation = topicApi.registerTopic(topicRequest)
                .expectBody(TopicResponse.class)
                .returnResult()
                .getResponseHeaders().getLocation();

        ResponseSpec response = topicApi.getTopic(newTopicLocation);

        itShouldFindTheNewTopic(response);
        TopicResponse topic = topicApi.getTopicFromResponse(response);
        isShouldConfirmTheNewTopicDetails(topicRequest, topic);
    }

    private void itShouldFindTheNewTopic(ResponseSpec response) {
        response
                .expectStatus()
                .isOk();
    }

    private void isShouldConfirmTheNewTopicDetails(RegisterTopicRequest topicRequest, TopicResponse topic) {
        assertThat(topic.getTitle()).isEqualTo(topicRequest.getTitle());
        assertThat(topic.getMessage()).isEqualTo(topicRequest.getMessage());
        assertThat(topic.getCreatedAt()).isEqualTo(topicRequest.getCreatedAt());
        assertThat(topic.getStatus()).isEqualTo(topicRequest.getStatus());
    }
}
