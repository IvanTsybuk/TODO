package org.based.controller;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.based.application.UserService;
import org.based.domain.User;
import org.jetbrains.annotations.NotNull;
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
    @NotNull
    UserService userService;
    public UserController(@NotNull UserService userService) {
        log.info("TaskController initialization");
        this.userService = userService;
    }
    @GetMapping
    public @NotNull List<User> findAll() {
        log.info("GetMapping: find all users");
        return userService.findAll();
    }
    @GetMapping("/{name}")
    public void findByName(@PathVariable @NotNull String name) {
        log.info(String.format("GetMapping: find a user by name - %s", name));
        userService.findByName(name);
    }
    @PostMapping
    public void save(@RequestBody @NotNull User user) {
        log.info("PostMapping: save new user");
        userService.save(user);
    }
    @PutMapping
    private void update(@RequestBody @NotNull User user) {
        log.info(String.format("PutMapping: update user - %s", user.getName()));
        userService.update(user);
    }
    @DeleteMapping("/{name}")
    public void delete(@RequestParam @NotNull String name) {
        log.info(String.format("DeleteMapping: delete user by name - %s", name));
        userService.deleteByName(name);
    }
}
