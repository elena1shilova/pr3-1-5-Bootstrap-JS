package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    User findById(Long id);

    void saveUser(User user);

    void deleteUser(Long id);

    User findByEmail(String username);

    void updateUser(User user);

    List<Role> roleList();
}
