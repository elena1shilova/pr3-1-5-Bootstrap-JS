package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.Usersdetails;

import java.util.List;

@Controller
public class UserController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/usersss")
    public String userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usersdetails usersdetails = (Usersdetails) authentication.getPrincipal();
        //System.out.println(usersdetails.getUsername());
        //System.out.println(usersdetails.getUser());

        return "index";
    }

}
