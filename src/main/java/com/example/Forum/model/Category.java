package com.example.Forum.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long categoryId;
    private String categoryName;
    private String categoryDescription;

    @OneToMany(mappedBy = "category")
    private List<Thread> threadList;

    public Category(){
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Thread> getThreadList() {
        return threadList;
    }

    public void setThreadList(List<Thread> threadList) {
        this.threadList = threadList;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}