package com.example.Forum.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}