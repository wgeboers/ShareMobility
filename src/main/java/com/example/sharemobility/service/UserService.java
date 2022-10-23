package com.example.sharemobility.service;

import com.example.sharemobility.domain.User;
import com.example.sharemobility.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Retrieve user based on username en password
    // return -1L of no valid user is found, else return user id
    public Long userLogin(String username, String password) {
       List<User> user = userRepository.findByUsernameContainsIgnoreCase(username);
       if (user.size() != 1) {
           return -1L;
       }

       if (isCorrectPassword(user.get(0), password)) {
           return user.get(0).getId();
       }

       return -1L;
    }

    private boolean isCorrectPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

}
