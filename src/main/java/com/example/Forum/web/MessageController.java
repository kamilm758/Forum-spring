package com.example.Forum.web;

import com.example.Forum.model.Category;
import com.example.Forum.model.Message;
import com.example.Forum.model.Thread;
import com.example.Forum.model.User;
import com.example.Forum.model.helpers.CategoryModel;
import com.example.Forum.model.helpers.MessageModel;
import com.example.Forum.model.helpers.ThreadModel;
import com.example.Forum.service.MailService;
import com.example.Forum.service.MessageService;
import com.example.Forum.service.ThreadService;
import com.example.Forum.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping("/message")
public class MessageController {

    Logger logger = Logger.getLogger(MessageController.class.getName());

    private MessageService messageService;
    private ThreadController threadController;
    private IndexController indexController;
    private UserService userService;
    private ThreadService threadService;
    private MailService mailService;
    @Autowired
    public MessageController(MessageService messageService, ThreadService threadService, MailService mailService,IndexController indexController, ThreadController threadController, UserService userService) {
        this.messageService = messageService;
        this.indexController = indexController;
        this.threadController = threadController;
        this.threadService = threadService;
        this.userService = userService;
        this.mailService = mailService;

        try {
            FileHandler fileHandler = new FileHandler("default.log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Utworzono instancje message controllera");

    }

    @RequestMapping("/new")
    public String createMessage(@ModelAttribute(value = "formModel") MessageModel messageModel, Model model) throws MessagingException {
        String username =((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.findByUsername(username);

        Message message = new Message();
        message.setMessageAuthor(user);
        message.setCreationDate(new Date());
        message.setModificationDate(new Date());
        message.setThreadId(messageModel.getThreadId());
        message.setMessageContent(messageModel.getMessageContent());

        Thread thread = threadService.getThreadById(message.getThreadId());

        User author = thread.getAuthor();

        messageService.createMsg(message);

        mailService.sendMail(author.getEmail(),
                "ktos odpowiedział w twoim wątku",
                "musisz zobaczyć to ASAP",
                false);

        logger.info("Utworzono nową wiadomość");
        return "redirect:/thread/show/"+message.getThreadId();

    }

    @RequestMapping("/editt")
    public String updateMessage(@ModelAttribute(value = "msg") Message message, Model model){
        String username =((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.findByUsername(username);

        Message message1 = messageService.findMessageById(message.getMessageId());
        message1.setModificationDate(new Date());
        message1.setMessageContent(message.getMessageContent());

        messageService.updateMessaga(message1);
        logger.info("zaktualizowano wiadomosc");
        return "redirect:/thread/show/"+message1.getThreadId();

    }


    @RequestMapping("/edit/{messageId}")
    public String editThread (@PathVariable(value = "messageId") Long id, Model model){
        Message message = messageService.findMessageById(id);
        model.addAttribute("msg", message);
        logger.info("edytowano wiadomosc");
        return "message/edit";

    }



}