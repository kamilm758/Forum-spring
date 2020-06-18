package com.example.Forum.web;

import com.example.Forum.model.Message;
import com.example.Forum.model.helpers.MessageModel;
import com.example.Forum.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/message")
public class MessageController {

    private MessageService messageService;
    private ThreadController threadController;
    private IndexController indexController;

    public MessageController(MessageService messageService, IndexController indexController, ThreadController threadController) {
        this.messageService = messageService;
        this.indexController = indexController;
        this.threadController = threadController;
    }

    @RequestMapping("/new")
    public String createMessage(@ModelAttribute(value = "formModel") MessageModel messageModel, Model model){

        Message message = new Message();
        message.setAuthorId(1L);
        message.setCreationDate(new Date());
        message.setModificationDate(new Date());
        message.setThreadId(messageModel.getThreadId());
        message.setMessageContent(messageModel.getMessageContent());

        messageService.createMsg(message);

        return threadController.showThread(message.getThreadId(),model);

    }
}