package com.example.sharemobility.repository;

import com.example.sharemobility.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUsernameContainsIgnoreCase(String username);
    List<User> findByUsernameEqualsIgnoreCaseAndUsernameEquals(String username, String password);

    User findByUsername(String username);

}
