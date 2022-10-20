package com.example.sharemobility.repository;

import com.example.sharemobility.domain.CarOwner;
import com.example.sharemobility.domain.CarUser;
import com.example.sharemobility.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    private User user1;

    private User user2;

//    @BeforeEach
//    public void initEach() {
//        userRepository.deleteAll();
//        userRepository.flush();
//
//        user1 = new CarUser("blotr", "letmein", "Bilbo", "Baggins", "The Shire 1", 10);
//        user2 = new CarOwner("clotr", "letmein2", "Frodo", "Baggins", "The Shire2");
//
//        userRepository.save(user1);
//        userRepository.save(user2);
//    }

    @Test
    void findByUsernameContainsIgnoreCase() {
        List<User> users = userRepository.findByUsernameContainsIgnoreCase("cLoTR");
        assertEquals(1,users.size());
    }

    @Test
    void findUserById() {
        Long id = user1.getId();
        Optional<User> testResult = userRepository.findById(id);
        assertEquals("Bilbo", testResult.get().getFirstname());
    }

}
