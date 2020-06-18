package com.example.Forum.model;

import javax.persistence.*;

import java.util.Date;

@Entity
public class

Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long threadId;
    private String threadName;
    private String threadContent;
    private Date creationDate;

    private boolean threadState=true;

    @ManyToOne
    @JoinColumn(name = "id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Thread() {
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getThreadContent() {
        return threadContent;
    }

    public void setThreadContent(String threadContent) {
        this.threadContent = threadContent;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    public boolean isThreadState() {
        return threadState;
    }

    public void setThreadState(boolean threadState) {
        this.threadState = threadState;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}