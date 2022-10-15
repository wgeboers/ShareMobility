package com.example.sharemobility.service;

import com.example.sharemobility.domain.User;
import com.example.sharemobility.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.PropertyResourceBundle;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isValidLogin(String username, String password) {
        User user = userRepository.findByUsername(username);
        return isCorrectPassword(user, password);
    }

    public boolean isCorrectPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

}
