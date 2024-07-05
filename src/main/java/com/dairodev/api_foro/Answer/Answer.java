package com.dairodev.api_foro.Answer;

import com.dairodev.api_foro.Topic.model.Topic;
import com.dairodev.api_foro.User.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Answer")
@Table(name = "answers")
public class Answer {
    private @Id UUID id;
    private String message;
    private LocalDate createdAt;
    private String solution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
}
