package com.example.Forum.service;

import com.example.Forum.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}