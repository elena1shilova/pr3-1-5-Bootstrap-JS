package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.Usersdetails;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }

//    @GetMapping("/usersss")
//    public String userInfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Usersdetails usersdetails = (Usersdetails) authentication.getPrincipal();
//        //System.out.println(usersdetails.getUsername());
//        //System.out.println(usersdetails.getUser());
//
//        return "index";
//    }
@GetMapping("/usersss")
public String userInfo(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Usersdetails users1 = (Usersdetails) authentication.getPrincipal();
    //List<User> users = userService.index();
    //System.out.println(usersdetails.getUsername());
    //System.out.println(usersdetails.getUser());
    model.addAttribute("users", users1.getUser());
    return "index";
}
}
