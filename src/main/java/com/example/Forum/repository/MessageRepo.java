package com.example.Forum.repository;

import com.example.Forum.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo  extends JpaRepository<Message, Long> {

    Iterable<Message> findAllByThreadId(Long id);
}
