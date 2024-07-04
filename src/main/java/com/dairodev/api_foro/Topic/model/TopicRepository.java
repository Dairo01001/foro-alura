package com.dairodev.api_foro.Topic.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {

    Boolean existsByTitleAndMessage(String title, String message);

    Page<Topic> findByStatusTrueOrderByCreatedAtAsc(Pageable pageable);
}
