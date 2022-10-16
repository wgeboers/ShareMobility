package com.example.sharemobility.controller;

import com.example.sharemobility.domain.User;
import com.example.sharemobility.repository.UserRepository;
import com.example.sharemobility.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll(@RequestParam(required = false) String username) {
        List<User> found = new ArrayList<>();

        if (username == null) {
            found.addAll(userRepository.findAll());
        } else {
            found.addAll(userRepository.findByUsernameContainsIgnoreCase(username));
        }
        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(found);
    }

    @GetMapping("/login")
    public ResponseEntity<User> getByUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
        User found = userRepository.findUserById(userService.userLogin(username, password));
        logger.error("Values of username and password: " + username + " : " + password );

        if (found == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id) {
        Optional<User> found = userRepository.findById(id);

        if (found.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(found);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User newUser) {
        if (userRepository.findByUsernameContainsIgnoreCase(newUser.getUsername()).isEmpty() ) {
            try {
                User user = userRepository.save(newUser);
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<User> update(@PathVariable Long id, @RequestBody User userNewValues) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFirstname(userNewValues.getFirstname());
            user.setLastname(userNewValues.getLastname());
            user.setPassword(userNewValues.getPassword());
            user.setAddress(userNewValues.getAddress());
            user.setUsername(userNewValues.getUsername());

            return ResponseEntity.ok(userRepository.save(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

