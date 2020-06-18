package com.example.Forum.model.helpers;

import java.util.Date;

public class MessageModel {

    private String messageContent;
    private Long authorId;
    private Long threadId;



    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public MessageModel() {
    }


}
