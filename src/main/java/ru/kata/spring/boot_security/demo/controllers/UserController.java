package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.models.User;

import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUser", userService.allUser());
        return "admin";
    }

    @PostMapping("/admin")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             Model model) {
        if(action.equals("delete")) {
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergtList(userId));
        return "admin";
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
/*@GetMapping("/index")
public String index() {
    return "index";
}
@GetMapping("/usersss")
public String userInfo(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //User users1 = (User) authentication.getPrincipal();

    User users = (User) authentication.getPrincipal();
    //System.out.println(usersdetails.getUsername());
    //System.out.println(usersdetails.getUser());
    model.addAttribute("users", users);
    return "index";
}*/
}
