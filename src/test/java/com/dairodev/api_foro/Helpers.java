package com.dairodev.api_foro;

import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

public class Helpers {

    public static String baseUri(long port) {
        return "http://localhost:" + port;
    }

    public static WebTestClient newWebClient(long port) {
        return WebTestClient
                .bindToServer()
                .baseUrl(baseUri(port))
                .responseTimeout(Duration.ofSeconds(90))
                .build();
    }

}
