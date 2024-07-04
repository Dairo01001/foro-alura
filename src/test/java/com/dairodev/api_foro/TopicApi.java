package com.dairodev.api_foro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import java.net.URI;
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
}
