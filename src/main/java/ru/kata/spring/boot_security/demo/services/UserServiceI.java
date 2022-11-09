package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserServiceI extends UserDetailsService {
    //@Override
    public UserDetails loadUserByUsername(String username);

    public User findUserById(Long userId);

    public List<User> allUsers();

    public User show(Long id);

    public void delete(Long id);

    public void saveUser1(User user);


    public boolean newUser(User user);
}
