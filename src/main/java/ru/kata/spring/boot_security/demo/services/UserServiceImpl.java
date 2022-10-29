package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositopies.UserRepository;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//    @Override
//    public Person show(Long id) {
//        return personRepository.findById(id).orElse(null);
//    }
    @Override
    public List<User> index() {
        return userRepository.findAll();
    }
//    @Override
//    public  Person save(Person person) {
//        return personRepository.save(person);
//    }
//    @Override
//    public void delete(Long id) {
//        personRepository.deleteById(id);
//    }

}
