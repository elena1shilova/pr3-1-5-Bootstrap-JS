package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.RoleServiceI;
import ru.kata.spring.boot_security.demo.services.UserServiceI;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.IntStream;

@Controller
//@RestController
public class UserController {
    private final UserServiceI userService;

    private final RoleServiceI roleService;

    public UserController(UserServiceI userService, RoleServiceI roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

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

    @GetMapping("admin/edit/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.show(id));
        model.addAttribute("roles", roleService.allRoles());
        return "edit";
    }

    @PostMapping("admin/edit")
    public String update(@RequestParam(value = "roles") Long[] roles, User user) {
        List<Role> rolesString = roleService.allRoles();
        List<Role> rolesString11 = new ArrayList<>(4);
        List<Role> roles1 = new ArrayList<>(4);
        for (int i = 0; i < roles.length; i++) {
            roles1.add(rolesString.get((roles[i].intValue()-1)));
        }
        user.setRoles(new HashSet<>(roleService.saveAll(roles1)));
        userService.saveUser1(user);

        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUSer(Model model) {
        model.addAttribute("userForm", new User());
        model.addAttribute("roles", roleService.allRoles());
        return "/new";
    }

    @PostMapping("/new")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/new";
        }

        if (!userService.newUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "/new";
        }

        return "redirect:/admin";
    }

}
