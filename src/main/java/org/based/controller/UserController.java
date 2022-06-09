package org.based.controller;

import java.util.List;
import org.based.application.UserService;
import org.based.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
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
    @PutMapping("/update")
    public void update(@RequestBody User user) {
        userService.update(user);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam String name) {
        userService.deleteByName(name);
    }
}
