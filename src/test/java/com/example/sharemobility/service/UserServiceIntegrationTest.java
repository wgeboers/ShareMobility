package com.example.sharemobility.service;

import com.example.sharemobility.domain.CarUser;
import com.example.sharemobility.domain.User;
import com.example.sharemobility.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        List<User> users = new ArrayList<>();
        User pandora = new CarUser("pand","dontletmeout", "Pand", "Ora", "The Hills", 10);
        pandora.setId(3L);
        users.add(pandora);
        Mockito.when(userRepository.findByUsernameContainsIgnoreCase("pand")).thenReturn(users);
    }

    @Test
    public void whenUsernameAndPassword_thenIdShouldBeReturned() {
        Long id = userService.userLogin("pand", "dontletmeout");
        assertEquals(3L, id);

    }

}
