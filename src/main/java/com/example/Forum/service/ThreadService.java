package com.example.Forum.service;

import com.example.Forum.model.Thread;
import com.example.Forum.model.helpers.ThreadModel;
import com.example.Forum.repository.ThreadRepo;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    public Thread createThread(ThreadModel threadModel) {
        Thread thread = new Thread();
        if(threadModel.getThreadId()!=null)
            thread.setThreadId(threadModel.getThreadId());
        thread.setAuthorId(1L);
//        if(threadModel.getCategoryId()!=null)
//            thread.setCategoryId(1L);

        thread.setThreadContent(threadModel.getThreadContent());
        thread.setThreadName(threadModel.getThreadTopic());
        thread.setCreationDate(new Date());

        return threadRepo.save(thread);
    }



    public void deleteThread(Long threadId) {
        threadRepo.deleteById(threadId);
        return;
    }
}