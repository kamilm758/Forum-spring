package com.example.Forum.service;

import com.example.Forum.model.Category;
import com.example.Forum.model.Message;
import com.example.Forum.repository.MessageRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {
    @Mock
    MessageRepo messageRepoMock;

    @InjectMocks
    MessageService messageServiceImpl;

    @Test
    public void checkFindMessageById(){
        Message exampleMessage = new Message();
        exampleMessage.setMessageId(10L);
        exampleMessage.setMessageContent("Example content");
        when(messageRepoMock.findByMessageId(10L)).thenReturn(exampleMessage);

        Message resultMessage = messageServiceImpl.findMessageById(10L);

        Assert.assertEquals(Long.toString(10L),resultMessage.getMessageId().toString());
        Assert.assertEquals("Example content",exampleMessage.getMessageContent());
    }
}
