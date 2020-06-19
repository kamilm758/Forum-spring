package com.example.Forum.service;


import com.example.Forum.model.User;
import com.example.Forum.repository.RoleRepository;
import com.example.Forum.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepositoryMock;

    @Mock
    RoleRepository roleRepositoryMock;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Test
    public void checkFindUserByUsername(){
        User exampleUser = new User();
        exampleUser.setUsername("kamilm758");
        exampleUser.setPassword("SomePassword");
        exampleUser.setEmail("k@email.pl");

        when(userRepositoryMock.findByUsername("kamilm758")).thenReturn(exampleUser);

        User expectedUser = userServiceImpl.findByUsername("kamilm758");

        Assert.assertEquals("kamilm758", expectedUser.getUsername());
        Assert.assertEquals("SomePassword", expectedUser.getPassword());
        Assert.assertEquals("k@email.pl", expectedUser.getEmail());
    }
}
