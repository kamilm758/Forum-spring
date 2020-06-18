package com.example.Forum.model.helpers;


import com.example.Forum.model.Category;
import com.example.Forum.model.Thread;

public class CategoryThreads {
    private Category category;
    private Iterable<Thread> threads;

    public CategoryThreads(Category category, Iterable<Thread> threads) {
        this.category = category;
        this.threads = threads;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Iterable<Thread> getThreads() {
        return threads;
    }

    public void setThreads(Iterable<Thread> threads) {
        this.threads = threads;
    }
}
