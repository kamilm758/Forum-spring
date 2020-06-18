package com.example.Forum.repository;

import com.example.Forum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category getByCategoryId(Long id);
}
