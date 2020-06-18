package com.example.Forum.web;

import com.example.Forum.model.Category;
import com.example.Forum.model.Thread;
import com.example.Forum.model.helpers.CategoryModel;
import com.example.Forum.model.helpers.CategoryThreads;
import com.example.Forum.model.helpers.ThreadViewModel;
import com.example.Forum.service.CategoryService;
import com.example.Forum.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/home")
public class IndexController {

    CategoryService categoryService;
    ThreadService threadService;

    @Autowired
    public IndexController(CategoryService categoryService, ThreadService threadService){
        this.threadService = threadService;
        this.categoryService = categoryService;
    }

    @GetMapping("/index")
    public String getAllCategories(Model model){

        Iterable<Category> categories = categoryService.getAll();
        Iterable<Thread> threads = threadService.getAllThreads();
        ArrayList<CategoryThreads> categoryThreads = new ArrayList<CategoryThreads>();
        StreamSupport.stream(categories.spliterator(),false)
                .forEach(category -> {
                    Iterable<Thread> threadsHelper = StreamSupport.stream(threads.spliterator(),false)
                            .filter(thread -> thread.getCategoryId().equals(category.getCategoryId()))
                            .collect(Collectors.toList());
                    categoryThreads.add(new CategoryThreads(category,threadsHelper));
                });

        model.addAttribute("categories",categoryThreads);
        model.addAttribute("choosenThread", new ThreadViewModel());
        model.addAttribute("Cat", new CategoryModel());

        return "home/index.html";
    }



    @GetMapping("/")
    public String index(Principal principal, Model model) {
        return  this.getAllCategories(model);
    }
}
