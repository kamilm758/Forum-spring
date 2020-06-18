package com.example.Forum.web;

import com.example.Forum.model.Category;
import com.example.Forum.model.Message;
import com.example.Forum.model.Thread;
import com.example.Forum.model.helpers.CategoryModel;
import com.example.Forum.model.helpers.MessageModel;
import com.example.Forum.model.helpers.ThreadModel;
import com.example.Forum.repository.MessageRepo;
import com.example.Forum.service.CategoryService;
import com.example.Forum.service.ThreadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/thread")
public class ThreadController {

    private MessageRepo messageRepo;
    private ThreadService threadService;
    private CategoryService categoryService;
    private IndexController indexController;


    public ThreadController(MessageRepo messageRepo, ThreadService threadService, CategoryService categoryService, IndexController indexController) {
        this.messageRepo = messageRepo;
        this.threadService = threadService;
        this.categoryService = categoryService;
        this.indexController = indexController;
    }

    @RequestMapping("/show/{threadId}")
    public String showThread1(@PathVariable("threadId") Long threadId, Model model) {
        ThreadModel threadModel = new ThreadModel();
        Iterable<Message> messages = messageRepo.findAllByThreadId(threadId);
        Thread thread = threadService.getThreadById(threadId);

        if(thread !=null) {
            threadModel.setCategoryId(thread.getCategoryId());
            threadModel.setThreadContent(thread.getThreadContent());
            threadModel.setThreadTopic(thread.getThreadName());

            model.addAttribute("msgs", messages);
            model.addAttribute("thread", threadModel);
            MessageModel messageModel = new MessageModel();
            messageModel.setThreadId(threadId);
            model.addAttribute("formModel", messageModel);
            return "thread/thread.html";
        }
        else
            return "home/index.html";
    }

    @GetMapping("new/{categoryId}")
    public String createNewThread(@PathVariable("categoryId") Long categoryId, Model model) {
        ThreadModel threadModel =new ThreadModel();
        threadModel.setCategoryId(categoryId);

        model.addAttribute("model", threadModel);
        return"thread/create.html";
    }

    @RequestMapping("/create")
    public String createNewThread(@ModelAttribute(value = "model") ThreadModel threadModel, Model model) {

        threadService.createThread(threadModel);

        return indexController.getAllCategories(model);
    }
    @RequestMapping("/delete/{threadId}")
    public String deleteThread (@PathVariable(value = "threadId") Long id, Model model){
        threadService.deleteThread(id);
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
        threadModel.setCurrentCategory(thread.getCategoryId());
        threadModel.setCategoryModels(categoryModels);
        threadModel.setCategoryId(thread.getCategoryId());
        threadModel.setThreadTopic(thread.getThreadName());
        threadModel.setThreadContent(thread.getThreadContent());
        threadModel.setThreadId(thread.getThreadId());
        model.addAttribute("model", threadModel);
        return"thread/create.html";

    }


    public String showThread(Long threadId, Model model) {
        ThreadModel threadModel = new ThreadModel();
        Iterable<Message> messages = messageRepo.findAllByThreadId(threadId);
        Thread thread = threadService.getThreadById(threadId);

        if(thread !=null) {
            threadModel.setCategoryId(thread.getCategoryId());
            threadModel.setThreadContent(thread.getThreadContent());
            threadModel.setThreadTopic(thread.getThreadName());

            model.addAttribute("msgs", messages);
            model.addAttribute("thread", threadModel);
            MessageModel messageModel = new MessageModel();
            messageModel.setThreadId(threadId);
            model.addAttribute("formModel", messageModel);
            return "thread/thread.html";
        }
        else
            return "home/index.html";
    }

    @RequestMapping("/report/{threadId}")
    public String reportThread (@PathVariable(value = "threadId") Long id, Model model){
        threadService.deleteThread(id);
        return indexController.getAllCategories(model);
    }
}