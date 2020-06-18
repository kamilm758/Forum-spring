package com.example.Forum.service;

import com.example.Forum.model.Message;
import com.example.Forum.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {

    private MessageRepo messageRepo;
    @Autowired
    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public ResponseEntity<Message> createMsg(Message message) {
        message.setCreationDate(new Date());
        message.setModificationDate(new Date());
        return  ResponseEntity.ok().body(messageRepo.save(message));
    }

    public Iterable<Message> findMessagesByThreadId(Long threadId){
        return messageRepo.findAllByThreadId(threadId);
    }
}
