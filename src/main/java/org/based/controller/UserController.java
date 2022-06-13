package org.based.controller;

import java.util.List;
import org.based.application.UserService;
import org.based.domain.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }
    @GetMapping("/{name}")
    public void findByName(@PathVariable String name) {
        userService.findByName(name);
    }
    @PostMapping
    public void save(@RequestBody User user) {
        if (user.getId() != 0) {
            update(user);
        }
        userService.save(user);
    }
    @PutMapping
    private void update(@RequestBody User user) {
        userService.update(user);
    }
    @DeleteMapping("/{name}")
    public void delete(@RequestParam String name) {
        userService.deleteByName(name);
    }
}
