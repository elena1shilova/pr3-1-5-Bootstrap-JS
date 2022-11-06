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
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import ru.kata.spring.boot_security.demo.repositopies.UserRepository;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public String userList1(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("users", user);
        return "user";
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());

        return "admin";
    }

    @GetMapping("admin/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

//    @GetMapping("/admin/gt/{userId}")
//    public String  gtUser(@PathVariable("userId") Long userId, Model model) {
//        model.addAttribute("allUsers", userService.usergtList(userId));
//        return "admin";
//    }

//    @GetMapping("admin/edit/{id}")
//    public String update(@PathVariable("id") Long id, Model model) {
//        User user = userService.show(id);
//
//        model.addAttribute("user", user);
//        model.addAttribute("allRoles", userService.allRoles());
//        return "edit";
//    }
//@GetMapping("admin/edit/{id}")
//public String update(@PathVariable("id") Long id, Model model) {
//    User user = userService.show(id);
//    List<Role> roles = userService.allRoles();
//    model.addAttribute("user", user);
//    model.addAttribute("allRoles", roles);
//    return "edit";
//}
@GetMapping("admin/edit/{id}")
public String update(@PathVariable("id") Long id, Model model) {
    User user = userService.show(id);
    List<Role> role = userService.allRoles();
    model.addAttribute("user", user);
    model.addAttribute("allRoles", role);
    return "edit";
}

    @PostMapping("admin/edit")
    public String update(User user) {

        userService.saveUser1(user);

        return "redirect:/admin";
    }
//    @PostMapping("admin/edit")
//    public String update(User user) {
//        userService.save(user);
//        return "redirect:/admin";
//    }





}
