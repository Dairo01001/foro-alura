package com.dairodev.api_foro;

import java.util.UUID;

public class TopicResponse {
    private UUID id;

    TopicResponse() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
