package com.example.Forum.service;

import com.example.Forum.model.Category;
import com.example.Forum.model.Role;
import com.example.Forum.model.Thread;
import com.example.Forum.model.User;
import com.example.Forum.model.helpers.ThreadModel;
import com.example.Forum.repository.ThreadRepo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ThreadService {

    private ThreadRepo threadRepo;

    public ThreadService(ThreadRepo threadRepo) {
        this.threadRepo = threadRepo;
    }

    public Thread getThreadById(Long id){
        return threadRepo.findByThreadId(id);
    }

    public Iterable<Thread> getAllThreads() {
        return threadRepo.findAll();
    }

    public Thread createThread(ThreadModel threadModel, Category category, User user) {
        Thread thread = new Thread();
        if(threadModel.getThreadId()!=null){
            thread.setThreadId(threadModel.getThreadId());
        }
        Object authentication = SecurityContextHolder.getContext().getAuthentication();

        thread.setAuthor(user);
        thread.setCategory(category);

        thread.setThreadContent(threadModel.getThreadContent());
        thread.setThreadName(threadModel.getThreadTopic());
        thread.setCreationDate(new Date());

        return threadRepo.save(thread);
    }



    public void deleteThread(Long threadId,User user) {

        Thread thread = threadRepo.findByThreadId(threadId);

        List<Role> list =StreamSupport.stream(user.getRoles().spliterator(),false)
                .filter(role -> role.getName().equals("Administrator"))
                .collect(Collectors.toList());

        if(thread.getAuthor().equals(user) || list.size()>0)
        {
            threadRepo.deleteById(threadId);
        }


    }
}