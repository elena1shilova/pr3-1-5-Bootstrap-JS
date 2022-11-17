package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

//@CrossOrigin
@RestController
//@RequestMapping("/api")
public class AdminRESTController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminRESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admin")
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }

//    @GetMapping("api/admin")
//    public List<User> showAllUser() {
//        List<User> all = userService.allUsers();
//        return all;
//    }
    @GetMapping("/api/admin")
    public ResponseEntity<List<User>> getUserForAdminPage() {
        List<User> users = userService.allUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/api/roles")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.allRoles(), HttpStatus.OK);
    }

    //Получаем пользователя по id
    @GetMapping("/api/admin/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.show(id);
    }

   /*@PostMapping(value = "/api/admin")
    public ResponseEntity<User> addUserAction(@RequestBody User user) {
        userService.newUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/
       @PostMapping("api/admin")
   public User addUser(@RequestBody User user) {
        userService.saveUser1(user);
        return user;
    }
    @PutMapping("/api/admin")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.saveUser1(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    @GetMapping("/user/")
//    public User getUser(@PathVariable Long id) {
//        User user = userService.findUserById(id);
//        return user;
//    }
    //поиск записи по id
//    @GetMapping(value = "/user/{id}")
//    public ResponseEntity<?> findNumberById(@PathVariable Long id) {
//        User user = userService.show(id);
//        return user != null
//                ? ResponseEntity.ok(user)
//                : ResponseEntity.ok().body(HttpStatus.NOT_FOUND);
//    }
@GetMapping("/user")
public ModelAndView userPage() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("user");
    return modelAndView;
}
@GetMapping("api/user")
public ResponseEntity<User> getUser(Principal principal) {
    return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
}



//
//    @PutMapping("api/admin")
//    public User updateUser(@RequestBody User user) {
//        userService.saveUser1(user);
//        return user;
//    }



   /* @DeleteMapping("/admin/{id}")
    public void deleteUser(@PathVariable Long id) {
        //User user = userService.findUserById(id);

        userService.delete(id);
                //"User with ID = " + id + " was deleted";
    }*/

//    @PostMapping("/new")
//    public User addUser(@RequestBody List<Role> roles, User user) {
//        user.setRoles(new HashSet<>(roleService.saveAll(roles)));
//        userService.saveUser1(user);
//        return user;
//    }
}
