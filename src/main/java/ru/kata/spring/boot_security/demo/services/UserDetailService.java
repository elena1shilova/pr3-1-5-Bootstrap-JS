package ru.kata.spring.boot_security.demo.services;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositopies.UserRepository;
import ru.kata.spring.boot_security.demo.security.Usersdetails;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;


    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> users = userRepository.findByName(username);
        if(users.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден!");
        }
        return new Usersdetails(users.get());
    }
}
