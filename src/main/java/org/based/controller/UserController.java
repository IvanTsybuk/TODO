package org.based.controller;

import java.util.List;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class UserController {
    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User> findAll() {
        log.info("GetMapping: finding all users");
        return userService.findAll();
    }
    @GetMapping("/{name}")
    public void findByName(@PathVariable String name) {
        log.info("GetMapping: finding a user by name");
        userService.findByName(name);
    }
    @PostMapping
    public void save(@RequestBody User user) {
        log.info("PostMapping: saving new user");
        userService.save(user);
    }
    @PutMapping
    private void update(@RequestBody User user) {
        log.info("PutMapping: updating user");
        userService.update(user);
    }
    @DeleteMapping("/{name}")
    public void delete(@RequestParam String name) {
        log.info("DeleteMapping: deleting user");
        userService.deleteByName(name);
    }
}
