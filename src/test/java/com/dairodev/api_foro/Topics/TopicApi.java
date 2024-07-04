package com.dairodev.api_foro.Topics;

import com.dairodev.api_foro.Helpers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Lazy
@Component
public class TopicApi {

    @Value(value = "${local.server.port}")
    private int port;

    private static final String TOPICS_PATH = "/topics";

    public URI uriForTopicsId(UUID id) {
        return URI.create(Helpers.baseUri(port) + TOPICS_PATH + "/" + id);
    }

    public ResponseSpec registerTopic(RegisterTopicRequest topicRequest) {
        return Helpers.newWebClient(port)
                .post()
                .uri(TOPICS_PATH)
                .bodyValue(topicRequest)
                .exchange();
    }

    public ResponseSpec getTopic(URI topicUri) {
        return Helpers.newWebClient(port)
                .get()
                .uri(topicUri)
                .exchange();
    }

    public TopicResponse getTopicFromResponse(ResponseSpec response) {
        return response
                .expectBody(TopicResponse.class)
                .returnResult()
                .getResponseBody();
    }

    public ResponseSpec getTopic(UUID id) {
        return getTopic(uriForTopicsId(id));
    }

    public ResponseSpec getTopics() {
        return Helpers.newWebClient(port)
                .get()
                .uri(TOPICS_PATH)
                .exchange();
    }

    public List<TopicResponse> getTopicsFromResponse(ResponseSpec response) {
        return response
                .expectBodyList(TopicResponse.class)
                .returnResult()
                .getResponseBody();
    }
}
