package com.example.Forum.web;

import com.example.Forum.model.Category;
import com.example.Forum.model.Message;
import com.example.Forum.model.Thread;
import com.example.Forum.model.User;
import com.example.Forum.model.helpers.CategoryModel;
import com.example.Forum.model.helpers.MessageModel;
import com.example.Forum.model.helpers.ThreadModel;
import com.example.Forum.repository.MessageRepo;
import com.example.Forum.service.CategoryService;
import com.example.Forum.service.ThreadService;
import com.example.Forum.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping("/thread")
public class ThreadController {


    Logger logger = Logger.getLogger(MessageController.class.getName());
    private MessageRepo messageRepo;
    private ThreadService threadService;
    private CategoryService categoryService;
    private IndexController indexController;
    private UserService userService;

    public ThreadController(MessageRepo messageRepo, ThreadService threadService, UserService userService, CategoryService categoryService, IndexController indexController) {
        this.messageRepo = messageRepo;
        this.threadService = threadService;
        this.categoryService = categoryService;
        this.indexController = indexController;
        this.userService = userService;

        try {
            FileHandler fileHandler = new FileHandler("default.log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Utworzono instancje Thread controllera");
    }

    @RequestMapping("/show/{threadId}")
    public String showThread1(@PathVariable("threadId") Long threadId, Model model) {
        ThreadModel threadModel = new ThreadModel();
        Iterable<Message> messages = messageRepo.findAllByThreadId(threadId);
        Thread thread = threadService.getThreadById(threadId);

        if(thread !=null) {
            threadModel.setCategoryId(thread.getCategory().getCategoryId());
            threadModel.setThreadContent(thread.getThreadContent());
            threadModel.setThreadTopic(thread.getThreadName());

            model.addAttribute("msgs", messages);
            model.addAttribute("thread", threadModel);
            MessageModel messageModel = new MessageModel();
            messageModel.setThreadId(threadId);
            model.addAttribute("formModel", messageModel);
            return "thread/thread";
        }
        else
            return "home/index";
    }

    @GetMapping("new/{categoryId}")
    public String createNewThread(@PathVariable("categoryId") Long categoryId, Model model) {
        ThreadModel threadModel =new ThreadModel();
        threadModel.setCategoryId(categoryId);

        model.addAttribute("model", threadModel);
        return"thread/create";
    }

    @RequestMapping("/create")
    public String createNewThread(@ModelAttribute(value = "model") ThreadModel threadModel, Model model) {
        String username =((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.findByUsername(username);
        Category category = categoryService.getCategoryById(threadModel.getCategoryId());

        threadService.createThread(threadModel,category, user);
        logger.info("Utworzono nowy wątek na forum");
        return indexController.getAllCategories(model);
    }
    @RequestMapping("/delete/{threadId}")
    public String deleteThread (@PathVariable(value = "threadId") Long id, Model model){
        String username =((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.findByUsername(username);
        threadService.deleteThread(id,user);
        return indexController.getAllCategories(model);
    }

    @RequestMapping("/edit/{threadId}")
    public String editThread (@PathVariable(value = "threadId") Long id, Model model){
        Thread thread = threadService.getThreadById(id);
        Iterable<Category> categories = categoryService.getAll();
        List<CategoryModel>  categoryModels= new ArrayList<>();

        for (Category cat:categories) {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setCategoryId(cat.getCategoryId());
            categoryModel.setCategoryDescription(cat.getCategoryDescription());
            categoryModel.setCategoryName(cat.getCategoryName());
            categoryModels.add(categoryModel);
        }

        ThreadModel threadModel =new ThreadModel();
        threadModel.setCurrentCategory(thread.getCategory().getCategoryId());
        threadModel.setCategoryModels(categoryModels);
        threadModel.setCategoryId(thread.getCategory().getCategoryId());
        threadModel.setThreadTopic(thread.getThreadName());
        threadModel.setThreadContent(thread.getThreadContent());
        threadModel.setThreadId(thread.getThreadId());
        model.addAttribute("model", threadModel);
        return"thread/create";

    }

    @GetMapping
    public String showThread(Long threadId, Model model) {
        ThreadModel threadModel = new ThreadModel();
        Iterable<Message> messages = messageRepo.findAllByThreadId(threadId);
        Thread thread = threadService.getThreadById(threadId);

        if(thread !=null) {
            threadModel.setCategoryId(thread.getCategory().getCategoryId());
            threadModel.setThreadContent(thread.getThreadContent());
            threadModel.setThreadTopic(thread.getThreadName());

            model.addAttribute("msgs", messages);
            model.addAttribute("thread", threadModel);
            MessageModel messageModel = new MessageModel();
            messageModel.setThreadId(threadId);
            model.addAttribute("formModel", messageModel);
            return "thread/thread";
        }
        else
            return "home/index";
    }

//    @RequestMapping("/report/{threadId}")
//    public String reportThread (@PathVariable(value = "threadId") Long id, Model model){
//        threadService.deleteThread(id);
//        return indexController.getAllCategories(model);
//    }
}