package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserServiceI extends UserDetailsService {

    UserDetails loadUserByUsername(String username);

    User findUserById(Long userId);

    List<User> allUsers();

    User show(Long id);

    void delete(Long id);

    void saveUser1(User user);

    boolean newUser(User user);
}
