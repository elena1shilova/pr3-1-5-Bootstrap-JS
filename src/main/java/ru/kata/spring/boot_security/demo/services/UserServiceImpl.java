package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositopies.RoleRepository;
import ru.kata.spring.boot_security.demo.repositopies.UserRepository;

import java.util.*;
import java.util.stream.Collectors;


@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Как ищем пользователя
    public User findByEmail(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void updateUser(User user) {
        User userDB = findById(user.getId());
        if (!(passwordEncoder.matches(user.getPassword(), userDB.getPassword()))
                && (user.getPassword() != null)
                && !(user.getPassword().equals(""))) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(userDB.getPassword());
        }
        userRepository.save(user);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = Optional.ofNullable(findByEmail(username));
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        } else throw new UsernameNotFoundException("User with username: " + username + "not found!");
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    @Override
    public List<User> findAllUsers() {

        return userRepository.findAll();
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Role> roleList() {
        return roleRepository.findAll();
    }


}
