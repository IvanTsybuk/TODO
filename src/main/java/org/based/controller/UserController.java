package org.based.controller;

import java.util.List;
import org.based.application.EntityService;
import org.based.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    EntityService<User> userService;
    public UserController(EntityService<User> userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }
    @PostMapping("/save")
    public void save(@RequestBody User user) {
        userService.save(user);
    }
    @GetMapping("/findUser")
    public void findUser(@RequestParam String name) {
        userService.findByName(name);
    }
}
