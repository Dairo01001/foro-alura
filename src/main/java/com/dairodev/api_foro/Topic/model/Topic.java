package com.dairodev.api_foro.Topic.model;

import java.util.UUID;

public class Topic {
    private UUID id;

    public Topic() {}

    public Topic(UUID id) {
        super();
        setId(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static Topic create() {
        return new Topic(UUID.randomUUID());
    }
}
