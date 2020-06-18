package com.example.Forum.model.helpers;

import java.util.ArrayList;
import java.util.List;

public class ThreadModel {
    private Long userId;
    private Long categoryId;
    private Long threadId;
    private Long currentCategory;



    private String threadTopic;
    private String threadContent;

    private List<CategoryModel> categoryModels = new ArrayList<>();


    public ThreadModel() {
    }


    public Long getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Long currentCategory) {
        this.currentCategory = currentCategory;
    }
    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public List<CategoryModel> getCategoryModels() {
        return categoryModels;
    }

    public void setCategoryModels(List<CategoryModel> categoryModels) {
        this.categoryModels = categoryModels;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getThreadTopic() {
        return threadTopic;
    }

    public void setThreadTopic(String threadTopic) {
        this.threadTopic = threadTopic;
    }

    public String getThreadContent() {
        return threadContent;
    }

    public void setThreadContent(String threadContent) {
        this.threadContent = threadContent;
    }
}
