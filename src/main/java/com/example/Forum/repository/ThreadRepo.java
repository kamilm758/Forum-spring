package com.example.Forum.repository;

import com.example.Forum.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepo  extends JpaRepository<Thread, Long> {
    Thread findByThreadId(Long id);

}
